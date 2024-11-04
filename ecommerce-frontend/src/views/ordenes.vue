<script setup>
import Orden from '@/components/orden.vue';
import { ref, onMounted } from 'vue';
import axios from 'axios';

const ordenes = ref([]);

const fetchOrdenes = async () => {
  try {
    const response = await axios.get("http://localhost:8090/ordenes/");
    ordenes.value = response.data;
  } catch (error) {
    console.error("Error fetching ordenes:", error);
  }
};

const createOrdenes = async () => {
  try {
    const response = await axios.post("http://localhost:8090/ordenes/", newOrden.value);
    ordenes.value.push(response.data);
    newOrden.value = { fecha: '', total: 0, cliente: null };
  } catch (error) {
    console.error("Error creating orden:", error);
  }
};

onMounted(fetchOrdenes);
</script>

<template>
  <v-container class="ordenes-page" fluid>
    <v-row class="header" justify="center" align="center">
      <v-col cols="12" class="text-center">
        <h2 class="title">Órdenes</h2>
      </v-col>
    </v-row>

    <v-row class="ordenes-section" justify="center">
      <v-col cols="12">
        <v-row class="container" dense>
          <v-col v-for="orden in ordenes" :key="orden.idOrden" cols="12" sm="6" md="4" lg="3">
            <Orden :orden="orden" />
          </v-col>
        </v-row>
      </v-col>
    </v-row>

    <v-row class="form-section" justify="center">
      <v-col cols="12" md="6">
        <v-form @submit.prevent="createOrden" class="orden-form">
          <v-text-field v-model="newOrden.fecha" label="Fecha" outlined clearable />
          <v-text-field v-model="newOrden.total" label="Total" outlined clearable />
          <v-text-field v-model="newOrden.cliente" label="Cliente" outlined clearable />
          <v-btn color="primary" @click="createOrden">Crear Orden</v-btn>
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
