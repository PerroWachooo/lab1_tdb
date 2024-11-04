<script setup>
import DetalleOrden from '@/components/detalleorden.vue';
import { ref, onMounted } from 'vue';
import axios from 'axios';

const detallesOrden = ref([]);

const fetchDetallesOrden = async () => {
  try {
    const response = await axios.get("http://localhost:8090/detalleordenes/");
    detallesOrden.value = response.data;
  } catch (error) {
    console.error("Error fetching detallesOrden:", error);
  }
};

const createDetalleOrden = async () => {
  try {
    const response = await axios.post("http://localhost:8090/detalleordenes/", newDetalleOrden.value);
    detallesOrden.value.push(response.data);
    newDetalleOrden.value = { cantidad: 0, precio: 0, producto: null, orden: null };
  } catch (error) {
    console.error("Error creating detalleOrden:", error);
  }
};

onMounted(fetchDetallesOrden);
</script>

<template>
  <v-container class="detalles-orden-page" fluid>
    <v-row class="header" justify="center" align="center">
      <v-col cols="12" class="text-center">
        <h2 class="title">Detalles de Orden</h2>
      </v-col>
    </v-row>

    <v-row class="detalles-orden-section" justify="center">
      <v-col cols="12">
        <v-row class="container" dense>
          <v-col v-for="detalle in detallesOrden" :key="detalle.idDetalle" cols="12" sm="6" md="4" lg="3">
            <DetalleOrden :detalleOrden="detalle" />
          </v-col>
        </v-row>
      </v-col>
    </v-row>

    <v-row class="form-section" justify="center">
      <v-col cols="12" md="6">
        <v-form @submit.prevent="createDetalleOrden" class="detalle-orden-form">
          <v-text-field v-model="newDetalleOrden.cantidad" label="Cantidad" outlined clearable />
          <v-text-field v-model="newDetalleOrden.precio" label="Precio" outlined clearable />
          <v-text-field v-model="newDetalleOrden.producto" label="Producto" outlined clearable />
          <v-text-field v-model="newDetalleOrden.orden" label="Orden" outlined clearable />
          <v-btn color="primary" @click="createDetalleOrden">Crear Detalle de Orden</v-btn>
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>


<style scoped>
.categorias-page {
  background: linear-gradient(135deg, #1a2a6c, #b21f1f, #fdbb2d);
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

/* Container for Categories */
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
.categoria-form {
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
