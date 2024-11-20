<template>
  <v-card class="detalle-card">
    <v-card-title>Orden ID: {{ detalle.id_detalle }}</v-card-title>
    <v-card-subtitle>Producto ID: {{ detalle.id_producto }}</v-card-subtitle>
    <v-card-text>
      Cantidad: {{ detalle.cantidad }}<br />
      Precio Unitario: {{ detalle.precio_unitario.toFixed(2) }}
    </v-card-text>
    <v-card-actions>
      <v-btn color="primary" @click="editarDetalle">Editar</v-btn>
      <v-btn color="error" @click="eliminarDetalle">Eliminar</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  props: {
    detalle: {
      type: Object,
      required: true,
    },
  },
  methods: {
    editarDetalle() {
      this.$emit('editarDetalle', this.detalle);
    },
    eliminarDetalle() {
      this.$emit('eliminarDetalle', this.detalle.id_detalle);
    },
  },
};
</script>

<style scoped>
.detalle-card {
  padding: 20px;
  margin-bottom: 20px;
  background: rgba(255, 255, 255, 0.9); /* Ajuste para mejorar la visibilidad */
  box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.5);
  border-radius: 8px;
}

.v-card-actions {
  display: flex;
  justify-content: space-between;
  padding-top: 10px;
}

.v-btn {
  flex: 1;
  margin: 0 5px;
}
</style>
