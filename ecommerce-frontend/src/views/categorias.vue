<script setup>
import Categoria from '@/components/categoria.vue';
</script>

<template>
  <v-container class="categorias-page" fluid>
    <!-- Encabezado -->
    <v-row class="header" justify="center" align="center">
      <v-col cols="12" class="text-center">
        <h2 class="title">Categorías</h2>
      </v-col>
    </v-row>

    <!-- Sección de Categorías -->
    <v-row class="categorias-section" justify="center">
      <v-col cols="12">
        <div class="container">
          <Categoria
            v-for="categoria in categorias"
            :key="categoria.id"
            :categoria="categoria"
          />
        </div>
      </v-col>
    </v-row>

    <!-- Formulario para crear categorías -->
    <v-row class="form-section" justify="center">
      <v-col cols="12" md="6">
        <form @submit.prevent="createCategoria" class="categoria-form">
          <input type="text" v-model="newCategoriaName" placeholder="Nombre de la nueva categoría" />
          <button type="submit">Crear Categoría</button>
        </form>
      </v-col>
    </v-row>
  </v-container>
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
          nombre: this.newCategoriaName,
        });
        this.categorias.push(response.data);
        this.newCategoriaName = "";
      } catch (error) {
        console.error("Error creating categoria:", error);
      }
    },
  },
};
</script>

<style scoped>
.categorias-page {
  background: linear-gradient(135deg, #1a2a6c, #b21f1f, #fdbb2d);
  color: hwb(272 7% 68%);
  min-height: 100vh;
  padding-top: 20px;
  font-family: 'Poppins', sans-serif;
}

.header .title {
  font-size: 2.5rem;
  color: #ff6ec7;
  text-shadow: 0px 0px 8px rgba(255, 110, 199, 0.8);
  font-weight: 700;
}

.container {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 20px;
  padding: 20px;
}

.container div {
  background: rgba(0, 0, 0, 0.7);
  padding: 15px;
  border-radius: 8px;
  box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.5);
  color: hsl(301, 74%, 12%);
  text-align: center;
  transition: transform 0.3s ease-in-out;
  min-width: 200px; /* Mantiene el tamaño mínimo */
  max-width: 250px; /* Limita el tamaño máximo */
}

.container div:hover {
  transform: scale(1.05);
  box-shadow: 0px 4px 20px rgba(255, 110, 199, 0.5);
}

.categoria-form {
  background: rgba(255, 255, 255, 0.1);
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.5);
  backdrop-filter: blur(10px);
  display: flex;
  flex-direction: column;
  gap: 10px;
}

input {
  padding: 10px;
  border-radius: 4px;
  border: 1px solid hsl(276, 87%, 12%);
  color: #29072e;
  background-color: rgba(0, 0, 0, 0.3);
}

input::placeholder {
  color: #ff6ec7;
}

button {
  padding: 10px;
  border-radius: 4px;
  background-color: #ff6ec7;
  color: #0e0732;
  border: none;
  cursor: pointer;
  font-weight: bold;
  text-shadow: 0px 0px 5px rgba(0, 0, 0, 0.2);
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #00d4ff;
}
</style>
