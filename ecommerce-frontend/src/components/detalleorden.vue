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

    <v-card-item>
      <v-card-title>Detalle Orden</v-card-title>
    </v-card-item>

    <v-card-text>
      <p><strong>Orden ID:</strong> {{ detalleOrden.orden.idOrden }}</p>
      <p><strong>Producto:</strong> {{ detalleOrden.producto.nombre }}</p>
      <p><strong>Cantidad:</strong> {{ detalleOrden.cantidad }}</p>
      <p><strong>Precio Unitario:</strong> {{ formatCurrency(detalleOrden.precioUnitario) }}</p>
    </v-card-text>

    <v-card-actions>
      <v-btn
        color="deep-purple-lighten-2"
        text="Actualizar"
        block
        border
        @click="actualizar"
      >
        Actualizar
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  props: {
    detalleOrden: {
      type: Object,
      required: true,
    },
    maxWidth: {
      type: [String, Number],
      default: 500,
    },
  },

  data() {
    return {
      loading: false,
    };
  },

  methods: {
    actualizar() {
      this.loading = true;
      setTimeout(() => (this.loading = false), 2000);
    },

    formatCurrency(value) {
      return new Intl.NumberFormat('es-CL', { style: 'currency', currency: 'CLP' }).format(value);
    },
  },
};
</script>

<style scoped>
p {
  margin: 0;
}
</style>
