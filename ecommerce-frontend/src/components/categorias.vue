<script setup>
import Categoria from './Categoria.vue';
</script>

<template>
    <div>
      <h2>Categorias</h2>
      <ul v-if="categorias.length">
        <Categoria v-for="categoria in categorias" :key="categoria.id" :categoria="categoria" />
      </ul>
      <div v-else>
        Loading categorias...
      </div>

        <form @submit.prevent="createCategoria">
            <input type="text" v-model="newCategoriaName" placeholder="New categoria name" />
            <button type="submit">Create</button>
        </form>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  
  export default {
    data() {
      return {
        categorias: [],
        newCategoriaName: "",
      };
    },
    mounted() {
      this.fetchCategorias();
    },
    methods: {
      async fetchCategorias() {
        try {
          const response = await axios.get("http://localhost:8090/api/v1/categorias");
          this.categorias = response.data;
        } catch (error) {
          console.error("Error fetching categorias:", error);
        }
      },
      async createCategoria() {
        try {
            const response = await axios.post("http://localhost:8090/api/v1/categorias", {
            nombre: this.newCategoriaName, // Usa "nombre" en lugar de "name" o cualquier otro nombre
            });
            this.categorias.push(response.data);
            this.newCategoriaName = ""; // Limpiar el campo después de agregar la categoría
        } catch (error) {
            console.error("Error creating categoria:", error);
        }
        }

    },
  };
  </script>