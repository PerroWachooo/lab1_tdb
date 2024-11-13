<script setup>
import Producto from '@/components/producto.vue';
import { ref, onMounted } from 'vue';
import axios from 'axios';

const productos = ref([]);
const categorias = ref([]);
const newProducto = ref({
  nombre: '',
  descripcion: '',
  precio: 0,
  stock: 0,
  estado: '',
  id_categoria: 0, // Cambiado para reflejar el ID de la categoría
});


// Fetch products
const fetchProductos = async () => {
  try {
    const response = await axios.get("http://localhost:8090/api/productos/");
    productos.value = response.data;
    console.log('Fetched productos:', JSON.stringify(productos.value, null, 2));
  } catch (error) {
    console.error("Error fetching productos:", error);
  }
};

// Fetch categories
const fetchCategorias = async () => {
  try {
    const response = await axios.get("http://localhost:8090/api/categorias/");
    categorias.value = response.data;
    console.log('Fetched categorias:', JSON.stringify(categorias.value, null, 2));
  } catch (error) {
    console.error("Error fetching categorias:", error);
  }
};

// Create a new product
const createProducto = async () => {
  
  console.log('Preparing to create new product:', JSON.stringify(newProducto.value, null, 2));

  // Asegurarse de que el payload tenga solo el idCategoria
  const payload = {
  nombre: newProducto.value.nombre,
  descripcion: newProducto.value.descripcion,
  precio: parseFloat(newProducto.value.precio),
  stock: parseInt(newProducto.value.stock),
  estado: newProducto.value.estado,
  id_categoria: newProducto.value.id_categoria
};


  console.log('Payload preparado para enviar:', JSON.stringify(payload, null, 2));

  try {
    const response = await axios.post("http://localhost:8090/api/productos/", payload);
    console.log('Product created successfully:', response.data);

    productos.value.push(response.data);
    newProducto.value = {
      nombre: '',
      descripcion: '',
      precio: 0,
      stock: 0,
      estado: '',
      categoria: null,
    };
  } catch (error) {
    console.error("Error creating producto:", error);
    if (error.response && error.response.data) {
      console.error("Detalles del error desde el backend:", error.response.data);
    }
    alert("Ocurrió un error al crear el producto. Por favor, revisa los datos ingresados.");
  }
};

onMounted(() => {
  fetchProductos();
  fetchCategorias();
});
</script>

<template>
  <v-container class="productos-page" fluid>
    <!-- Header Section -->
    <v-row class="header" justify="center" align="center">
      <v-col cols="12" class="text-center">
        <h2 class="title">Productos</h2>
      </v-col>
    </v-row>

    <!-- Products Section -->
    <Producto > </Producto>

    <!-- Form to Create Products -->
    <v-row class="form-section" justify="center">
      <v-col cols="12" md="6">
        <v-form @submit.prevent="createProducto" class="producto-form">
          <v-text-field v-model="newProducto.nombre" label="Nombre" outlined clearable />
          <v-text-field v-model="newProducto.descripcion" label="Descripción" outlined clearable />
          <v-text-field v-model.number="newProducto.precio" label="Precio" outlined clearable type="number" />
          <v-text-field v-model.number="newProducto.stock" label="Stock" outlined clearable type="number" />

          <!-- Estado select -->
          <v-select
            v-model="newProducto.estado"
            :items="['disponible', 'agotado']"
            label="Estado"
            outlined
            clearable
          ></v-select>

          <!-- Styled simple dropdown for Categoria -->
          <label for="categoria-select" class="styled-select-label">Categoría</label>
          <div class="styled-select">
            <select v-model="newProducto.id_categoria" id="categoria-select">
              <option value="" disabled>Seleccione una categoría</option>
              <option
                v-for="categoria in categorias"
                :key="categoria.id_categoria"
                :value="categoria.id_categoria"
              >
                {{ categoria.nombre }}
              </option>
            </select>

          </div>

          <v-btn color="primary" @click="createProducto">Crear Producto</v-btn>
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>
.productos-page {
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

/* Container for Products */
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
.producto-form {
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

/* Styled select dropdown to match Vuetify style */
.styled-select-label {
  font-size: 16px;
  color: #ffffff;
  margin-bottom: 8px;
  display: block;
}

.styled-select {
  position: relative;
  display: inline-block;
  width: 100%;
  background-color: rgba(255, 255, 255, 0.1);
  border: 1px solid rgba(255, 255, 255, 0.4);
  border-radius: 4px;
  overflow: hidden;
}

.styled-select select {
  width: 100%;
  padding: 10px;
  background: transparent;
  color: #000000;
  border: none;
  appearance: none;
  font-size: 16px;
  font-family: 'Poppins', sans-serif;
}

.styled-select select:focus {
  outline: none;
}

.styled-select::after {
  content: '▼';
  font-size: 12px;
  color: #ffffff;
  position: absolute;
  right: 10px;
  top: 50%;
  transform: translateY(-50%);
  pointer-events: none;
}

/* Button Styles */
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
