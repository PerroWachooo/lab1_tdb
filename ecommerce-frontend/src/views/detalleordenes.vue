<script setup>
import DetalleOrden from '@/components/detalleorden.vue';
import { ref, onMounted } from 'vue';
import axios from 'axios';

const detalleOrdenes = ref([]);
const ordenes = ref([]);
const productos = ref([]);
const backgroundImageSrc = ref(''); // Imagen de fondo

const newDetalleOrden = ref({
  orden: null,
  producto: null,
  cantidad: 1,
  precioUnitario: 0,
});

const editableDetalleOrden = ref(null);

// Fetch existing DetalleOrden entries
const fetchDetalleOrdenes = async () => {
  try {
    const response = await axios.get('http://localhost:8090/api/detalleorden/');
    detalleOrdenes.value = response.data;
  } catch (error) {
    console.error('Error fetching detalleOrdenes:', error);
  }
};

const deleteDetalleOrden = async (detalleOrdenId) => {
  try {
    await axios.delete(`http://localhost:8090/api/detalleorden/delete-detalle/${detalleOrdenId}`);
    detalleOrdenes.value = detalleOrdenes.value.filter((detalle) => detalle.id_detalle !== detalleOrdenId);
  } catch (error) {
    console.error('Error deleting detalleOrden:', error);
  }
};

// Fetch Orden options
const fetchOrdenes = async () => {
  try {
    const response = await axios.get('http://localhost:8090/api/orden/');
    ordenes.value = response.data;
  } catch (error) {
    console.error('Error fetching ordenes:', error);
  }
};

// Fetch Producto options
const fetchProductos = async () => {
  try {
    const response = await axios.get('http://localhost:8090/api/productos/');
    productos.value = response.data;
  } catch (error) {
    console.error('Error fetching productos:', error);
  }
};

// Create new DetalleOrden
const createDetalleOrden = async () => {
  const payload = {
    id_orden: newDetalleOrden.value.orden,
    id_producto: newDetalleOrden.value.producto,
    cantidad: newDetalleOrden.value.cantidad,
    precio_unitario: newDetalleOrden.value.precioUnitario,
  };

  console.log('Payload que se enviará al servidor:', JSON.stringify(payload, null, 2));

  try {
    const response = await axios.post('http://localhost:8090/api/detalleorden/', payload);
    console.log('DetalleOrden creado exitosamente:', response.data);

    // Agregar el nuevo DetalleOrden a la lista
    detalleOrdenes.value.push(response.data);

    // Reiniciar el formulario
    newDetalleOrden.value = { orden: null, producto: null, cantidad: 1, precioUnitario: 0 };
  } catch (error) {
    console.error('Error al crear el DetalleOrden:', error);
  }
};

const updateDetalleOrden = async (detalleOrden) => {
  try {
    const payload = {
      id_detalle: detalleOrden.id_detalle,
      id_orden: detalleOrden.orden,
      id_producto: detalleOrden.producto,
      cantidad: detalleOrden.cantidad,
      precio_unitario: detalleOrden.precioUnitario,
    };
    await axios.put(`http://localhost:8090/api/detalleorden/`, payload);
    console.log('DetalleOrden actualizado exitosamente:', detalleOrden);
    editableDetalleOrden.value = null; // Cerrar el modo de edición

    // Actualizar la lista de detalleOrdenes después de la edición
    await fetchDetalleOrdenes();
  } catch (error) {
    console.error('Error updating DetalleOrden:', error);
  }
};

const editDetalleOrden = (detalle) => {
  editableDetalleOrden.value = { ...detalle }; // Copiar el detalle para editarlo
};

const cancelEdit = () => {
  editableDetalleOrden.value = null;
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
  fetchDetalleOrdenes();
  fetchOrdenes();
  fetchProductos();
  fetchBackgroundImage('cyberpunk');
});
</script>


<template>
  <v-container class="detalle-ordenes-page" fluid>
    <!-- Parallax Background Section -->
    <v-parallax :src="backgroundImageSrc" height="auto" style="padding-top: 100px;">
      <!-- Header Section -->
      <v-row class="header" justify="center" align="center">
        <v-col cols="12" class="text-center">
          <h2 class="title">Detalle Ordenes</h2>
        </v-col>
      </v-row>
       <!-- DetalleOrdenes Section -->
      <v-row class="detalle-ordenes-section" justify="center">
        <v-col cols="12">
          <v-row class="container" dense>
            <v-col v-for="detalle in detalleOrdenes" :key="detalle.id_detalle" cols="12" sm="6" md="4" lg="3">
              <v-card class="detalle-card" outlined>
                <template v-if="editableDetalleOrden && editableDetalleOrden.id_detalle === detalle.id_detalle">
                  <!-- Edit Form -->
                  <v-card-title class="detalle-card-title">
                    Editando Orden ID: {{ editableDetalleOrden.id_orden }}
                  </v-card-title>
                  <v-card-text>
                    <!-- Orden Select Dropdown using native HTML select element, only ID -->
                    <label for="orden-select" class="styled-select-label">Orden</label>
                    <div class="styled-select">
                      <select v-model="editableDetalleOrden.orden" id="orden-select">
                        <option value="" disabled>Seleccione una orden</option>
                        <option v-for="orden in ordenes" :key="orden.id_orden" :value="orden.id_orden">
                          Orden ID: {{ orden.id_orden }} - {{ orden.fecha_orden }} - {{ orden.estado }}
                        </option>
                      </select>
                    </div>

                    <!-- Producto Select Dropdown using native HTML select element -->
                    <label for="producto-select" class="styled-select-label">Producto</label>
                    <div class="styled-select">
                      <select v-model="editableDetalleOrden.producto" id="producto-select">
                        <option value="" disabled>Seleccione un producto</option>
                        <option v-for="producto in productos" :key="producto.idProducto" :value="producto.idProducto">
                          {{ producto.idProducto }} - {{ producto.nombre }}
                        </option>
                      </select>
                    </div>
                    <v-text-field v-model="editableDetalleOrden.cantidad" label="Cantidad" type="number"></v-text-field>
                    <v-text-field v-model="editableDetalleOrden.precioUnitario" label="Precio Unitario" type="number"></v-text-field>
                  </v-card-text>
                  <v-card-actions>
                    <v-btn color="success" @click="() => updateDetalleOrden(editableDetalleOrden)">Guardar</v-btn>
                    <v-btn color="secondary" @click="cancelEdit">Cancelar</v-btn>
                  </v-card-actions>
                </template>
                <template v-else>
                  <!-- Display Mode -->
                  <v-card-title class="detalle-card-title">
                    Orden ID: {{ detalle.id_orden }}
                  </v-card-title>
                  <v-card-subtitle>
                    Producto ID: {{ detalle.id_producto }}
                  </v-card-subtitle>
                  <v-card-text>
                    Cantidad: {{ detalle.cantidad }}<br />
                    Precio Unitario: {{ detalle.precio_unitario }}
                  </v-card-text>
                  <v-card-actions>
                    <v-btn color="primary" @click="() => editDetalleOrden(detalle)">Editar</v-btn>
                    <v-btn color="error" @click="() => deleteDetalleOrden(detalle.id_detalle)">Eliminar</v-btn>
                  </v-card-actions>
                </template>
              </v-card>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    </v-parallax>

    <!-- Form to Create DetalleOrden -->
    <v-row class="form-section" justify="center">
      <v-col cols="12" md="6">
        <v-form @submit.prevent="createDetalleOrden" class="detalle-orden-form">
          
          <!-- Orden Select Dropdown using native HTML select element, only ID -->
          <label for="orden-select" class="styled-select-label">Orden</label>
          <div class="styled-select">
            <select v-model="newDetalleOrden.orden" id="orden-select">
              <option value="" disabled>Seleccione una orden</option>
              <option v-for="orden in ordenes" :key="orden.id_orden" :value="orden.id_orden">
                Orden ID: {{ orden.id_orden }} - {{ orden.fecha_orden }} - {{ orden.estado }}
              </option>
            </select>
          </div>

          <!-- Producto Select Dropdown using native HTML select element -->
          <label for="producto-select" class="styled-select-label">Producto</label>
          <div class="styled-select">
            <select v-model="newDetalleOrden.producto" id="producto-select">
              <option value="" disabled>Seleccione un producto</option>
              <option v-for="producto in productos" :key="producto.idProducto" :value="producto.idProducto">
                {{ producto.idProducto }} - {{ producto.nombre }}
              </option>
            </select>
          </div>

          <v-text-field v-model="newDetalleOrden.cantidad" label="Cantidad" outlined clearable type="number" />
          <v-text-field v-model="newDetalleOrden.precioUnitario" label="Precio Unitario" outlined clearable type="number" />

          <v-btn color="primary" @click="createDetalleOrden">Crear Detalle Orden</v-btn>
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>
/* Add consistent styles */
.detalle-ordenes-page {
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

/* Container for DetalleOrdenes */
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

/* Detalle Orden Card Styles */
.detalle-card {
  background: rgba(255, 255, 255, 0.8);
  color: #000;
  box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.5);
  transition: transform 0.3s ease-in-out;
}

.detalle-card:hover {
  transform: scale(1.05);
}

.detalle-card-title {
  font-weight: bold;
  color: #ff6ec7;
  text-shadow: 0px 0px 5px rgba(255, 110, 199, 0.8);
}

/* Form Styles */
.detalle-orden-form {
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
  margin-bottom: 15px;
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
  color: #ffffff;
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
