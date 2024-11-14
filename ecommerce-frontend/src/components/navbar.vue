<script setup>
import { ref } from 'vue';
import { RouterLink } from 'vue-router';
</script>

<template>
  <v-app-bar app color="primary">
    <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>
    <RouterLink to="/">
      <v-btn text>E-commerce</v-btn>
    </RouterLink>
    <v-spacer></v-spacer>
    
    <!-- Wrap v-btns in RouterLink for navigation -->
    <RouterLink to="/">
      <v-btn text>Home</v-btn>
    </RouterLink>
    <RouterLink to="/productos">
      <v-btn text>Productos</v-btn>
    </RouterLink>
    <RouterLink to="/clientes">
      <v-btn text>Clientes</v-btn>
    </RouterLink>
    <RouterLink to="/categorias">
      <v-btn text>Categorias</v-btn>
    </RouterLink>
    <RouterLink to="/ordenes">
      <v-btn text>Ordenes</v-btn>
    </RouterLink>
    <RouterLink to="/detalleordenes">
      <v-btn text>Detalle Ordenes</v-btn>
    </RouterLink>
  </v-app-bar>
</template>
