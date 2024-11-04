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
      <v-card-title>Orden #{{ orden.idOrden }}</v-card-title>
      <v-card-subtitle>{{ formatFecha(orden.fechaOrden) }}</v-card-subtitle>
    </v-card-item>

    <v-card-text>
      <p><strong>Cliente:</strong> {{ orden.cliente.nombre }}</p>
      <p><strong>Estado:</strong> {{ orden.estado }}</p>
      <p><strong>Total:</strong> {{ formatCurrency(orden.total) }}</p>
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
    orden: {
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

    formatFecha(fecha) {
      return new Date(fecha).toLocaleDateString('es-CL', {
        year: 'numeric',
        month: 'long',
        day: 'numeric',
        hour: '2-digit',
        minute: '2-digit',
      });
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
