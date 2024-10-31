<template>
  <v-card class="client-card mx-auto" :max-width="maxWidth">
    <v-toolbar color="deep-purple darken-4" dark>
      <v-toolbar-title>Clientes</v-toolbar-title>
    </v-toolbar>

    <!-- Lista de clientes estilo chat -->
    <v-list dense>
      <v-list-item v-for="cliente in clientes" :key="cliente.id" class="client-item">
        <v-list-item-avatar>
          <v-avatar
            size="40"
            :src="cliente.avatar || 'https://via.placeholder.com/40'"
          ></v-avatar>
        </v-list-item-avatar>
        <v-list-item-content>
          <v-list-item-title class="client-name">{{ cliente.nombre }}</v-list-item-title>
          <v-list-item-subtitle>
            {{ cliente.email }}
            {{ cliente.telefono }}
            {{ cliente.direccion }}
          </v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </v-card>
</template>

<script>
export default {
  props: {
    clientes: {
      type: Array,
      required: true,
    },
    maxWidth: {
      type: [String, Number],
      default: 450,
    },
  },
};
</script>

<style scoped>
.client-card {
  background: #1a1a1a;
  color: hsl(295, 81%, 12%);
  border-radius: 8px;
  box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.5);
}

.client-item {
  padding: 10px 0;
  border-bottom: 1px solid rgba(255, 255, 255, 0.1);
}

.client-name {
  font-weight: bold;
  color: #ff6ec7;
}

.v-list-item-subtitle {
  color: hsl(189, 51%, 29%);
}
</style>
