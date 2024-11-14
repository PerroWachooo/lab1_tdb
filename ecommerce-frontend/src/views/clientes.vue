<script setup>
import Cliente from '@/components/cliente.vue';
import { ref, onMounted } from 'vue';
import axios from 'axios';

const clientes = ref([]);
const newCliente = ref({ nombre: '', direccion: '', email: '', telefono: '' });

// Variables para las imágenes de fondo de cada cliente
const clienteImages = ref([]);
const backgroundImageSrc = ref(''); // Imagen de fondo

// Función para obtener los clientes desde la API
const fetchClientes = async () => {
  try {
    const response = await axios.get("http://localhost:8090/api/cliente/");
    clientes.value = response.data;
    fetchClienteImages(); // Llama la función para obtener las imágenes de los clientes
  } catch (error) {
    console.error("Error fetching clientes:", error);
  }
};

// Función para crear un nuevo cliente
const createCliente = async () => {
  try {
    const response = await axios.post("http://localhost:8090/api/cliente/", newCliente.value);
    clientes.value.push(response.data);
    newCliente.value = { nombre: '', direccion: '', email: '', telefono: '' };
    fetchClienteImages(); // Actualizar las imágenes tras añadir un cliente
  } catch (error) {
    console.error("Error creating cliente:", error);
  }
};

// Función para obtener las imágenes de los clientes desde Unsplash
const fetchClienteImages = async () => {
  try {
    const promises = clientes.value.map(async (cliente) => {
      const response = await fetch(
        `https://api.unsplash.com/search/photos?query=${cliente.nombre}&client_id=QkjMm1DzbXbkQDPZha7IrUSE_8UYBb-JHMrMbskJgis&per_page=1`
      );
      const data = await response.json();
      return data.results?.[0]?.urls.regular || 'https://example.com/default-client.jpg';
    });
    clienteImages.value = await Promise.all(promises);
  } catch (error) {
    console.error('Error fetching client images:', error);
    clienteImages.value = clientes.value.map(() => 'https://example.com/default-client.jpg');
  }
};

// Función para obtener una imagen de fondo para el parallax
const fetchBackgroundImage = async (query) => {
  try {
    const response = await fetch(
      `https://api.unsplash.com/search/photos?query=${query}&client_id=QkjMm1DzbXbkQDPZha7IrUSE_8UYBb-JHMrMbskJgis&per_page=1`
    );
    const data = await response.json();
    backgroundImageSrc.value = data.results?.[0]?.urls.regular || 'https://example.com/default-background.jpg';
  } catch (error) {
    console.error('Error fetching background image:', error);
    backgroundImageSrc.value = 'https://example.com/default-background.jpg';
  }
};

onMounted(() => {
  fetchClientes();
  fetchBackgroundImage('cyberpunk'); // Ajusta el término de búsqueda para la imagen de fondo
});
</script>

<template>
  <v-container class="clientes-page" fluid>
    <!-- Parallax Background Section -->
    <v-parallax :src="backgroundImageSrc" height="auto" style="padding-top: 100px;">
      <!-- Header Section -->
      <v-row class="header" justify="center" align="center">
        <v-col cols="12" class="text-center">
          <h2 class="title">Clientes</h2>
        </v-col>
      </v-row>
       <!-- Clients Section -->
    <v-row class="clientes-section" justify="center">
      <v-col cols="12">
        <v-row class="container" dense>
          <v-col v-for="(cliente, index) in clientes" :key="cliente.idCliente" cols="12" sm="6" md="4" lg="3">
            <Cliente :cliente="cliente" :backgroundImage="clienteImages[index]" />
          </v-col>
        </v-row>
      </v-col>
    </v-row>
    </v-parallax>


    <!-- Form to Create Clients -->
    <v-row class="form-section" justify="center">
      <v-col cols="12" md="6">
        <v-form @submit.prevent="createCliente" class="cliente-form">
          <v-text-field v-model="newCliente.nombre" label="Nombre" outlined clearable color="primary" />
          <v-text-field v-model="newCliente.direccion" label="Dirección" outlined clearable color="primary" />
          <v-text-field v-model="newCliente.email" label="Email" outlined clearable color="primary" />
          <v-text-field v-model="newCliente.telefono" label="Teléfono" outlined clearable color="primary" />
          <v-btn color="primary" @click="createCliente">Crear Cliente</v-btn>
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>
.clientes-page {
  background: linear-gradient(135deg, #360041, #1d0866, #2dccfd);
  color: #ffffff;
  min-height: 100vh;
  padding-top: 20px;
  font-family: 'Poppins', sans-serif;
}

/* Header Styles */
.header .title {
  font-size: 2.5rem;
  color: #ff6ec7;
  text-shadow: 0px 0px 8px rgba(255, 110, 199, 0.8);
  font-weight: 700;
}

/* Container for Clients */
.container {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  padding: 20px;
}

.container .v-col {
  display: flex;
  justify-content: center;
}

/* Form Styles */
.cliente-form {
  display: flex;
  flex-direction: column;
  gap: 15px;
  padding: 20px;
  border-radius: 8px;
  background: rgba(255, 255, 255, 0.1);
  box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.5);
  backdrop-filter: blur(10px);
}

.v-text-field input {
  color: #ffffff;
}

.v-btn {
  background-color: #ff6ec7;
  color: #0e0732;
  font-weight: bold;
  transition: background-color 0.3s ease;
}

.v-btn:hover {
  background-color: #00d4ff;
  color: #ffffff;
}
</style>
