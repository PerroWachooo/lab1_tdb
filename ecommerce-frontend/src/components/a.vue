<script setup>
import Cliente from './cliente.vue'; // Asegúrate de que el nombre esté en mayúsculas
import { ref, onMounted } from 'vue';
import axios from 'axios';

const clientes = ref([]);
const newClienteName = ref("");
const newClienteDireccion = ref("");
const newClienteEmail = ref("");
const newClienteTelefono = ref("");

// Fetch inicial de clientes al montar el componente
const fetchClientes = async () => {
  try {
    const response = await axios.get("http://localhost:8090/api/v1/clientes");
    clientes.value = response.data;
  } catch (error) {
    console.error("Error fetching clientes:", error);
  }
};

// Crear nuevo cliente
const crearCliente = async () => {
  try {
    const response = await axios.post("http://localhost:8090/api/v1/clientes", {
      nombre: newClienteName.value,
      direccion: newClienteDireccion.value,
      email: newClienteEmail.value,
      telefono: newClienteTelefono.value,
    });
    clientes.value.push(response.data);
    // Limpiar campos de entrada
    newClienteName.value = "";
    newClienteDireccion.value = "";
    newClienteEmail.value = "";
    newClienteTelefono.value = "";
  } catch (error) {
    console.error("Error creando cliente:", error);
  }
};

// Montar la función de fetch para cargar los clientes
onMounted(() => {
  fetchClientes();
});
</script>

<template>
  <div>
    <h2>Clientes</h2>

    <div class="clientes-container">
      <Cliente
        v-for="cliente in clientes"
        :key="cliente.idCliente"
        :cliente="cliente"
      />
    </div>

    <!-- Formulario para agregar nuevo cliente -->
    <form @submit.prevent="crearCliente">
      <input type="text" v-model="newClienteName" placeholder="Nombre del nuevo cliente" />
      <input type="text" v-model="newClienteDireccion" placeholder="Dirección del nuevo cliente" />
      <input type="email" v-model="newClienteEmail" placeholder="Email del nuevo cliente" />
      <input type="tel" v-model="newClienteTelefono" placeholder="Teléfono del nuevo cliente" />
      <button type="submit">Crear cliente</button>
    </form>
  </div>
</template>

<style>
.clientes-container {
  display: flex;
  flex-wrap: wrap;
  gap: 16px;
  justify-content: start;
  max-width: 100%;
  margin: 0 auto;
  padding: 16px;
}
</style>
