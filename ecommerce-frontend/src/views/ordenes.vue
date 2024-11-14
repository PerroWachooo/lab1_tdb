<template>
  <div class="vaporwave-gradient-background">
    <!-- Parallax Background Section -->
    <v-parallax :src="backgroundImageSrc" height="auto">
      <!-- Header Section -->
      <v-row class="header" justify="center" align="center">
        <v-col cols="12" class="text-center">
          <h2 class="title vaporwave-header-title">-</h2>
          <h2 class="title vaporwave-header-title">-</h2>
          <h2 class="title vaporwave-header-title">Administración de Órdenes</h2>
        </v-col>
      </v-row>
       <!-- Mostrar las órdenes -->
       <v-row>
        <v-col v-for="orden in ordenes" :key="orden.id_orden" cols="12" md="4">
          <v-card class="detalle-card vaporwave-card">
            <v-card-title class="detalle-card-title vaporwave-title">Orden #{{ orden.id_orden }}</v-card-title>
            <v-card-subtitle class="vaporwave-subtitle">
              Cliente: {{ obtenerClienteNombre(orden.cliente) }}
            </v-card-subtitle>
            <v-card-text class="vaporwave-text">
              Fecha: {{ orden.fecha_orden }}<br />
              Estado: {{ orden.estado }}<br />
              Total: ${{ orden.total }}
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
    </v-parallax>

    <v-container>
      <!-- Formulario para agregar una nueva orden -->
      <v-form ref="form" @submit.prevent="guardarOrden" class="vaporwave-form">
        <v-row>
          <v-col cols="12" md="6">
            <v-text-field
              v-model="nuevaOrden.fecha_orden"
              label="Fecha de la Orden"
              type="datetime-local"
              color="pink lighten-2"
              outlined
              required
            ></v-text-field>
          </v-col>
          <v-col cols="12" md="6">
            <v-select
              v-model="nuevaOrden.estado"
              :items="estados"
              label="Estado"
              color="teal lighten-2"
              outlined
              required
            ></v-select>
          </v-col>
          <!-- Cliente Select Dropdown usando un elemento select nativo de HTML -->
          <v-col cols="12" md="6">
            <label for="cliente-select" class="styled-select-label vaporwave-label">Cliente</label>
            <div class="styled-select vaporwave-select">
              <select v-model="nuevaOrden.cliente" id="cliente-select">
                <option value="" disabled>Seleccione un cliente</option>
                <option
                  v-for="cliente in clientes"
                  :key="cliente.id_cliente"
                  :value="cliente.id_cliente"
                >
                  {{ cliente.id_cliente }} - {{ cliente.nombre }}
                </option>
              </select>
            </div>
          </v-col>
          <v-col cols="12" md="6">
            <v-text-field
              v-model="nuevaOrden.total"
              label="Total"
              type="number"
              color="blue lighten-2"
              outlined
              required
            ></v-text-field>
          </v-col>
          <v-col cols="12">
            <v-btn class="vaporwave-btn" @click="guardarOrden">Guardar Orden</v-btn>
          </v-col>
        </v-row>
      </v-form>
      <v-divider class="my-4 vaporwave-divider"></v-divider>
    </v-container>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      ordenes: [],
      nuevaOrden: {
        fecha_orden: '',
        estado: '',
        cliente: null,
        total: null,
      },
      clientes: [],
      estados: ['DISPONIBLE', 'AGOTADO', 'PENDIENTE'],
      backgroundImageSrc: '', // Imagen de fondo para el parallax
    };
  },
  mounted() {
    this.fetchOrdenes();
    this.fetchClientes();
    this.fetchBackgroundImage('cyberpunk'); // Obtén una imagen con temática vaporwave para el parallax
  },
  methods: {
    async fetchOrdenes() {
      try {
        const response = await axios.get('http://localhost:8090/api/orden/');
        this.ordenes = response.data;
      } catch (error) {
        console.error('Error al obtener las órdenes:', error);
      }
    },
    async fetchClientes() {
      try {
        const response = await axios.get('http://localhost:8090/api/cliente/');
        this.clientes = response.data;
      } catch (error) {
        console.error('Error al obtener los clientes:', error);
      }
    },
    async guardarOrden() {
      try {
        const ordenToSend = {
          id_orden: this.ordenes.length + 1,
          fecha_orden: this.nuevaOrden.fecha_orden,
          estado: this.nuevaOrden.estado,
          cliente: this.nuevaOrden.cliente,
          total: parseFloat(this.nuevaOrden.total),
        };

        const response = await axios.post('http://localhost:8090/api/orden/', ordenToSend);
        this.ordenes.push(response.data);
        this.nuevaOrden = {
          fecha_orden: '',
          estado: '',
          cliente: null,
          total: null,
        };
      } catch (error) {
        console.error('Error al guardar la orden:', error);
      }
    },
    obtenerClienteNombre(clienteId) {
      const cliente = this.clientes.find((c) => c.id_cliente === clienteId);
      return cliente ? `${cliente.id_cliente} - ${cliente.nombre}` : 'Cliente no encontrado';
    },
    async fetchBackgroundImage(query) {
      try {
        const response = await fetch(
          `https://api.unsplash.com/search/photos?query=${query}&client_id=QkjMm1DzbXbkQDPZha7IrUSE_8UYBb-JHMrMbskJgis&per_page=1`
        );
        const data = await response.json();
        this.backgroundImageSrc = data.results?.[0]?.urls.regular || 'https://example.com/default-background.jpg';
      } catch (error) {
        console.error('Error al obtener la imagen de fondo:', error);
        this.backgroundImageSrc = 'https://example.com/default-background.jpg';
      }
    },
  },
};
</script>

<style scoped>
/* Estilo Vaporwave */
.vaporwave-gradient-background {
  background: linear-gradient(135deg, #1a2a6c, #1f9eb2, #fd2def);
  min-height: 100vh;
  padding: 20px;
}

.ordenes-container {
  background: linear-gradient(135deg, #1a2a6c, #1f9eb2, #fd2def);
  color: #ffffff;
  padding: 20px;
}

/* Contenedor del formulario */
.vaporwave-form {
  background: rgba(255, 255, 255, 0.1); /* Fondo semitransparente */
  border-radius: 15px;
  padding: 20px;
  box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.5);
  backdrop-filter: blur(10px);
}

/* Botones de Vaporwave */
.vaporwave-btn {
  background-color: #ff71ce; /* Color rosa neón */
  color: #0e0732;
  font-weight: bold;
  transition: background-color 0.3s ease;
  border-radius: 8px;
}

.vaporwave-btn:hover {
  background-color: #00d4ff; /* Cambio a azul neón */
  color: #ffffff;
}

/* Estilo del divisor vaporwave */
.vaporwave-divider {
  border-color: #ff71ce; /* Color rosa vibrante */
}

/* Estilo base de la tarjeta (detalle-card) */
.detalle-card.vaporwave-card {
  background: linear-gradient(135deg, #1a2a6c, #1f9eb2, #fd2def);
  color: #ffffff;
  box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.5);
  transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
  border-radius: 15px;
  overflow: hidden;
}

.detalle-card:hover {
  transform: scale(1.05);
  box-shadow: 0px 8px 25px rgba(0, 0, 0, 0.8);
}

/* Título de la tarjeta con estilo vaporwave */
.detalle-card-title.vaporwave-title {
  font-weight: bold;
  color: #ff71ce;
  text-shadow: 0px 0px 8px rgba(255, 113, 206, 1);
  font-size: 1.5rem;
  padding: 10px 15px;
}

/* Estilo del subtítulo de la tarjeta */
.vaporwave-subtitle {
  font-size: 1rem;
  color: #d4f5ff;
  margin: 0 15px;
  text-shadow: 0px 0px 6px rgba(212, 245, 255, 0.8);
}

/* Texto de la tarjeta */
.vaporwave-text {
  font-size: 1rem;
  color: #ffffff;
  padding: 10px 15px;
  text-shadow: 0px 0px 4px rgba(255, 255, 255, 0.7);
}

/* Select de cliente con estilo vaporwave */
.styled-select.vaporwave-select {
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
  font-size: 16px;
  font-family: 'Poppins', sans-serif;
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

/* Etiqueta del select */
.styled-select-label.vaporwave-label {
  font-size: 16px;
  color: #ff71ce;
  margin-bottom: 8px;
  display: block;
  text-shadow: 0px 0px 5px rgba(255, 113, 206, 1);
}

/* Título en la sección de Parallax */
.vaporwave-header-title {
  color: #ff71ce;
  font-weight: bold;
  text-shadow: 0px 0px 10px rgba(255, 113, 206, 1);
  font-size: 3rem;
}
</style>
