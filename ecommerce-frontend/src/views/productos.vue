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
  id_categoria: 0, // ID de la categoría
});

// Variables para las imágenes de fondo de cada producto
const productoImages = ref([]);
const backgroundImageSrc = ref(''); // Imagen de fondo

// Función para obtener los productos desde la API
const fetchProductos = async () => {
  try {
    const response = await axios.get("http://localhost:8090/api/productos/");
    productos.value = response.data;
    fetchProductoImages(); // Llama la función para obtener las imágenes de los productos
  } catch (error) {
    console.error("Error fetching productos:", error);
  }
};

// Función para obtener las categorías desde la API
const fetchCategorias = async () => {
  try {
    const response = await axios.get("http://localhost:8090/api/categorias/");
    categorias.value = response.data;
  } catch (error) {
    console.error("Error fetching categorias:", error);
  }
};

// Función para crear un nuevo producto
const createProducto = async () => {
  try {
    const payload = {
      nombre: newProducto.value.nombre,
      descripcion: newProducto.value.descripcion,
      precio: parseFloat(newProducto.value.precio),
      stock: parseInt(newProducto.value.stock),
      estado: newProducto.value.estado,
      id_categoria: newProducto.value.id_categoria,
    };
    const response = await axios.post("http://localhost:8090/api/productos/", payload);
    productos.value.push(response.data);
    newProducto.value = {
      nombre: '',
      descripcion: '',
      precio: 0,
      stock: 0,
      estado: '',
      id_categoria: 0,
    };
    fetchProductoImages(); // Actualizar las imágenes tras añadir un producto
  } catch (error) {
    console.error("Error creating producto:", error);
  }
};

// Función para obtener las imágenes de los productos desde Unsplash
const fetchProductoImages = async () => {
  try {
    const promises = productos.value.map(async (producto) => {
      const response = await fetch(
        `https://api.unsplash.com/search/photos?query=${producto.nombre}&client_id=QkjMm1DzbXbkQDPZha7IrUSE_8UYBb-JHMrMbskJgis&per_page=1`
      );
      const data = await response.json();
      return data.results?.[0]?.urls.regular || 'https://example.com/default-product.jpg';
    });
    productoImages.value = await Promise.all(promises);
  } catch (error) {
    console.error('Error fetching product images:', error);
    productoImages.value = productos.value.map(() => 'https://example.com/default-product.jpg');
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
  fetchProductos();
  fetchCategorias();
  fetchBackgroundImage('cyberpunk'); // Ajusta el término de búsqueda para la imagen de fondo
});
</script>

<template>
  <v-container class="productos-page" fluid>
    <!-- Parallax Background Section -->
    <v-parallax :src="backgroundImageSrc" height="auto" style="padding-top: 100px;">
      <!-- Header Section -->
      <v-row class="header" justify="center" align="center">
        <v-col cols="12" class="text-center">
          <h2 class="title">Productos</h2>
        </v-col>
      </v-row>
      <!-- Products Section -->
    <Producto > </Producto>
    </v-parallax>


    <!-- Form to Create Products -->
    <v-row class="form-section" justify="center">
      <v-col cols="12" md="6">
        <v-form @submit.prevent="createProducto" class="producto-form">
          <v-text-field v-model="newProducto.nombre" label="Nombre" outlined clearable color="primary" />
          <v-text-field v-model="newProducto.descripcion" label="Descripción" outlined clearable color="primary" />
          <v-text-field v-model.number="newProducto.precio" label="Precio" outlined clearable type="number" color="primary" />
          <v-text-field v-model.number="newProducto.stock" label="Stock" outlined clearable type="number" color="primary" />
          
          <!-- Estado select -->
          <v-select
            v-model="newProducto.estado"
            :items="['disponible', 'agotado']"
            label="Estado"
            outlined
            clearable
            color="primary"
          ></v-select>

          <!-- Styled simple dropdown for Categoria -->
          <label for="categoria-select" class="styled-select-label">Categoría</label>
          <div class="styled-select">
            <select v-model="newProducto.id_categoria" id="categoria-select">
              <option value="" disabled>Seleccione una categoría</option>
              <option
                v-for="categoria in categorias"
                :key="categoria.idCategoria"
                :value="categoria.idCategoria"
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
/* Card Styles */
.detalle-card {
  background: rgba(255, 255, 255, 0.8); /* Fondo semitransparente para dar sensación de ligereza */
  color: #000; /* Color de texto negro para buena legibilidad */
  box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.5); /* Sombra para dar un efecto de elevación */
  transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out; /* Transiciones para efectos suaves */
  border-radius: 10px; /* Bordes redondeados para hacer las tarjetas más amigables */
  overflow: hidden; /* Para que los elementos internos no se salgan del área de la tarjeta */
}

.detalle-card:hover {
  transform: scale(1.05); /* Aumenta ligeramente el tamaño al pasar el ratón */
  box-shadow: 0px 8px 20px rgba(0, 0, 0, 0.7); /* Aumenta la sombra al hacer hover para enfatizar la selección */
}

/* Card Title */
.detalle-card-title {
  font-weight: bold; /* Negrita para destacar el título */
  color: #ff6ec7; /* Color vibrante que contrasta con el fondo */
  text-shadow: 0px 0px 5px rgba(255, 110, 199, 0.8); /* Sombra para dar profundidad al título */
  font-size: 1.3rem; /* Tamaño adecuado para que destaque */
  padding: 10px 15px; /* Espaciado alrededor del título */
}

/* Card Subtitle */
.v-card-subtitle {
  font-size: 1rem; /* Tamaño del subtítulo más pequeño que el título */
  color: #6b6b6b; /* Un color más apagado para el subtítulo */
  margin: 0 15px; /* Espaciado lateral para alinear con el título */
}

/* Card Text */
.v-card-text {
  font-size: 1rem; /* Texto normal para el contenido principal */
  color: #333; /* Color de texto que asegura buena legibilidad */
  padding: 10px 15px; /* Espaciado interno para el texto */
}

/* Imagen de Producto (si deseas agregar una) */
.detalle-card-image {
  max-height: 150px; /* Altura máxima para las imágenes dentro de las tarjetas */
  width: 100%; /* Asegura que ocupe todo el ancho */
  object-fit: cover; /* Recorta la imagen para ajustarse al contenedor sin distorsionarse */
  border-bottom: 1px solid rgba(0, 0, 0, 0.1); /* Línea divisoria entre la imagen y el contenido */
}

/* Botones dentro de la Card */
.v-card-actions {
  display: flex; /* Flex para alinear los botones */
  justify-content: flex-end; /* Alinear los botones a la derecha */
  padding: 10px 15px; /* Espaciado interno */
}

.v-btn-card-action {
  background-color: #ff6ec7; /* Color del botón para coincidir con la temática */
  color: #ffffff; /* Texto blanco */
  font-weight: bold; /* Negrita para dar énfasis */
  transition: background-color 0.3s ease; /* Transición suave en el color */
}

.v-btn-card-action:hover {
  background-color: #00d4ff; /* Cambiar el color del botón al pasar el ratón */
}

</style>
