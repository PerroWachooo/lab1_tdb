<!-- Categoria.vue -->
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
        color="deep-purple-lighten-2"
        text="Buscar"
        block
        border
        @click="buscar"
      >
        Buscar
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
    imageSrc: {
      type: String,
      default: '', // Agrega aquí la URL de una imagen por defecto o ajusta según sea necesario
    },
  },

  data: () => ({
    loading: false,
  }),

  methods: {
    buscar() {
      this.loading = true;
      setTimeout(() => (this.loading = false), 2000);
    },
  },
};
</script>
