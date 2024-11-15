<script setup>
import { ref } from 'vue';
import { RouterLink } from 'vue-router';
</script>

<template>
  <v-app-bar
  app
  color="#0d0319"
  class="navbar-custom"
  elevate-on-scroll
>

    <v-app-bar-nav-icon @click="drawer = !drawer" color="pink-accent"></v-app-bar-nav-icon>
    <RouterLink to="/">
      <v-btn text class="title-btn">E-commerce</v-btn>
    </RouterLink>
    <v-spacer></v-spacer>
    
    <!-- Wrap v-btns in RouterLink for navigation -->
    <RouterLink to="/">
      <v-btn text class="nav-btn">Home</v-btn>
    </RouterLink>
    <RouterLink to="/productos">
      <v-btn text class="nav-btn">Productos</v-btn>
    </RouterLink>
    <RouterLink to="/clientes">
      <v-btn text class="nav-btn">Clientes</v-btn>
    </RouterLink>
    <RouterLink to="/categorias">
      <v-btn text class="nav-btn">Categorias</v-btn>
    </RouterLink>
    <RouterLink to="/ordenes">
      <v-btn text class="nav-btn">Ordenes</v-btn>
    </RouterLink>
    <RouterLink to="/detalleordenes">
      <v-btn text class="nav-btn">Detalle Ordenes</v-btn>
    </RouterLink>
  </v-app-bar>
</template>

<style scoped>
.navbar-custom {
  background-color: #0d0319; /* Púrpura oscuro sólido, sin transparencia */

  border-bottom: 2px solid #ff3399; /* Línea inferior con el tono rosa neón */
  padding: 8px 16px;
}



.title-btn {
  font-size: 1.5rem;
  font-weight: bold;
  color: #ff3399; /* Rosa neón para el título */
  text-transform: uppercase;
}

.nav-btn {
  color: #00aaff; /* Azul para el contraste */
  font-weight: bold;
  transition: color 0.3s ease;
}

.nav-btn:hover {
  color: #ff3399; /* Cambia al rosa neón cuando se pasa el ratón */
}

.v-app-bar-nav-icon {
  color: #ff3399; /* Color rosa neón para el icono del menú */
}

.v-btn {
  text-decoration: none; /* Quita subrayado */
}
</style>
