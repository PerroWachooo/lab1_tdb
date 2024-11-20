<template>
  <v-card :disabled="loading" :loading="loading" class="mx-auto my-12" :max-width="maxWidth">
    <template v-slot:loader="{ isActive }">
      <v-progress-linear :active="isActive" color="deep-purple" height="4" indeterminate></v-progress-linear>
    </template>

    <v-img :height="imageHeight" :src="imageSrc" cover></v-img>

    <v-card-item>
      <v-card-title>{{ categoria.nombre }}</v-card-title>
    </v-card-item>

    <v-card-actions>
      <v-btn color="blue lighten-2" text @click="editCategoria">Editar</v-btn>
      <v-btn color="red lighten-2" text @click="borrarCategoria">Borrar</v-btn>
    </v-card-actions>

    <v-dialog v-model="editDialog" max-width="500px">
      <v-card>
        <v-card-title>Editar Categoría</v-card-title>
        <v-card-text>
          <v-text-field v-model="editCategoriaData.nombre" label="Nombre de la categoría"></v-text-field>
        </v-card-text>
        <v-card-actions>
          <v-btn color="primary" @click="guardarCambios">Guardar</v-btn>
          <v-btn @click="editDialog = false">Cancelar</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-card>
</template>

<script>
import axios from 'axios';

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
      editDialog: false,
      editCategoriaData: {},
    };
  },

  watch: {
    categoria: {
      immediate: true,
      handler(newVal) {
        this.fetchImage(newVal.nombre);
        this.editCategoriaData = { ...newVal }; // Prepopulate edit data with current category
      },
    },
  },

  methods: {
    async fetchImage(query) {
      try {
        const response = await fetch(
          `https://api.unsplash.com/search/photos?query=${query}&client_id=QkjMm1DzbXbkQDPZha7IrUSE_8UYBb-JHMrMbskJgis&per_page=1`
        );
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
          await axios.delete(`http://localhost:8090/api/categorias/delete-categoria/${this.categoria.id_categoria}`);
          this.$emit('categoriaBorrada', this.categoria.id_categoria);
        } catch (error) {
          console.error('Error deleting category:', error);
        } finally {
          this.loading = false;
        }
      }
    },

    editCategoria() {
      this.editDialog = true;
    },

    async guardarCambios() {
      this.loading = true;
      try {
        await axios.put('http://localhost:8090/api/categorias/', this.editCategoriaData);
        this.$emit('categoriaActualizada', this.editCategoriaData);
      } catch (error) {
        console.error('Error updating category:', error);
      } finally {
        this.loading = false;
        this.editDialog = false;
      }
    },
  },
};
</script>
