<template>
  <v-card
    :disabled="loading"
    :loading="loading"
    class="mx-auto my-12"
    :max-width="maxWidth"
  >
    <template v-slot:loader="{ isActive }">
      <v-progress-linear
        :active="isActive"
        color="deep-purple"
        height="4"
        indeterminate
      ></v-progress-linear>
    </template>

    <v-img
      :height="imageHeight"
      :src="imageSrc"
      cover
    ></v-img>

    <v-card-item>
      <v-card-title>{{ categoria.nombre }}</v-card-title>
    </v-card-item>

    <v-card-actions>
      
      <v-btn
        color="red lighten-2"
        text="Borrar"
        block
        border
        @click="borrarCategoria"
      >
        Borrar
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  props: {
    categoria: {
      type: Object,
      required: true,
    },
    maxWidth: {
      type: [String, Number],
      default: 374,
    },
    imageHeight: {
      type: [String, Number],
      default: 200,
    },
  },

  data() {
    return {
      loading: false,
      imageSrc: '', // Initial empty image src
    };
  },

  watch: {
    categoria: {
      immediate: true,
      handler(newVal) {
        this.fetchImage(newVal.nombre);
      },
    },
  },

  methods: {
    buscar() {
      this.loading = true;
      setTimeout(() => (this.loading = false), 2000);
    },

    async fetchImage(query) {
      try {
        const response = await fetch(`https://api.unsplash.com/search/photos?query=${query}&client_id=QkjMm1DzbXbkQDPZha7IrUSE_8UYBb-JHMrMbskJgis&per_page=1`);
        const data = await response.json();
        if (data.results && data.results.length > 0) {
          this.imageSrc = data.results[0].urls.small;
        } else {
          this.imageSrc = 'https://example.com/default.jpg'; // Fallback image
        }
      } catch (error) {
        console.error('Error fetching image:', error);
        this.imageSrc = 'https://example.com/default.jpg'; // Fallback in case of error
      }
    },

    async borrarCategoria() {
      if (confirm(`¿Está seguro de que quiere borrar la categoría "${this.categoria.nombre}"?`)) {
        this.loading = true;
        try {
          await fetch(`http://localhost:8090/api/categorias/delete-categoria/${this.categoria.id_categoria}`, {
            method: 'DELETE',
          });
          this.$emit('categoriaBorrada', this.categoria.id_categoria);
        } catch (error) {
          console.error('Error deleting category:', error);
        } finally {
          this.loading = false;
        }
      }
    },
  },
};
</script>
