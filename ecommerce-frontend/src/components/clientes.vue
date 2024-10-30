<script setup>
import Cliente from './cliente.vue';
</script>

<template>
    <div>
      <h2>Clientes</h2>
      <ul v-if="clientes.length">
        <Cliente v-for="cliente in clientes" :key="cliente.id" :cliente="cliente" />
      </ul>
      <div v-else>
        Loading Clientes...
      </div>

        <form @submit.prevent="createCliente">
          <input type="text" v-model="newClienteName" placeholder="Nombre del nuevo cliente" />
          <input type="text" v-model="newClienteDireccion" placeholder="Dirección del nuevo cliente" />
          <input type="email" v-model="newClienteEmail" placeholder="Email del nuevo cliente" />
          <input type="tel" v-model="newClienteTelefono" placeholder="Teléfono del nuevo cliente" />
            <button type="submit">Create</button>
        </form>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  
  export default {
    data() {
      return {
        clientes: [],
        newClienteName: "",
        newClienteDireccion: "",
        newClienteEmail: "",
        newClienteTelefono: "",
      };
    },
    mounted() {
      this.fetchClientes();
    },
    methods: {
      async fetchClientes() {
        try {
          const response = await axios.get("http://localhost:8090/api/v1/clientes");
          this.clientes = response.data;
        } catch (error) {
          console.error("Error fetching clientes:", error);
        }
      },
      async createCliente() {
        try {
            const response = await axios.post("http://localhost:8090/api/v1/clientes", {
            nombre: this.newClienteName, // Usa "nombre" en lugar de "name" o cualquier otro nombre
            direccion: this.newClienteDireccion,
            email: this.newClienteEmail,
            telefono: this.newClienteTelefono,
            });
            //print the response in the console
            console.log(response.data);
            this.clientes.push(response.data);
            this.newClienteName = ""; // Limpiar el campo después de agregar la categoría
            this.newClienteDireccion = "";
            this.newClienteEmail = "";
            this.newClienteTelefono = "";

        } catch (error) {
            console.error("Error creating clientes:", error);
        }
        }

    },
  };
  </script>