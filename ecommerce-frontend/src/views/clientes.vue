<script setup>
import Cliente from '@/components/cliente.vue';
</script>

<template>
  <v-container class="clientes-page" fluid>
    <v-row class="header" justify="center" align="center">
      <v-col cols="12" class="text-center">
        <h2 class="title">Clientes</h2>
      </v-col>
    </v-row>

    <v-row class="clientes-section" justify="center">
      <v-col cols="12" md="6">
        <Cliente :clientes="clientes" />
      </v-col>
    </v-row>

    <v-row class="form-section" justify="center">
      <v-col cols="12" md="6">
        <form @submit.prevent="createCliente" class="client-form">
          <v-text-field v-model="newClienteName" label="Nombre del nuevo cliente" outlined />
          <v-text-field v-model="newClienteDireccion" label="Dirección del nuevo cliente" outlined />
          <v-text-field v-model="newClienteEmail" label="Email del nuevo cliente" outlined type="email" />
          <v-text-field v-model="newClienteTelefono" label="Teléfono del nuevo cliente" outlined type="tel" />
          <v-btn type="submit" color="deep-purple" block>Crear Cliente</v-btn>
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
        const response = await axios.get("http://localhost:8090/api/v1/clientes/");
        this.clientes = response.data;
      } catch (error) {
        console.error("Error fetching clientes:", error);
      }
    },
    async createCliente() {
      try {
        const response = await axios.post("http://localhost:8090/api/v1/clientes/", {
          nombre: this.newClienteName,
          direccion: this.newClienteDireccion,
          email: this.newClienteEmail,
          telefono: this.newClienteTelefono,
        });
        this.clientes.push(response.data);
        this.newClienteName = "";
        this.newClienteDireccion = "";
        this.newClienteEmail = "";
        this.newClienteTelefono = "";
      } catch (error) {
        console.error("Error creating cliente:", error);
      }
    },
  },
};
</script>
<style scoped>
.clientes-page {
  background: linear-gradient(135deg, #1a2a6c, #b21f1f, #fdbb2d);
  color: #ffffff;
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

.clientes-section {
  margin-top: 20px;
}

.client-form {
  background: rgba(255, 255, 255, 0.1);
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.5);
  backdrop-filter: blur(10px);
}

.v-btn {
  margin-top: 20px;
  color: #ffffff;
  background-color: #ff6ec7;
}

.v-btn:hover {
  background-color: #00d4ff;
}

/* Estilo para la tarjeta de clientes */
.v-col {
  background: rgba(0, 0, 0, 0.7); /* Fondo oscuro para resaltar el contenido */
  padding: 20px;
  border-radius: 12px;
  box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.5);
  margin: 0 20px; /* Margen para ver el fondo */
  color: #fff; /* Texto blanco para mejor contraste */
}

/* Estilo para cada cliente */
.cliente-item {
  border-bottom: 1px solid rgba(255, 255, 255, 0.2); /* Línea de separación entre clientes */
  padding: 10px 0;
}

/* Colores de texto ajustados */
.cliente-item .nombre {
  color: #ff6ec7; /* Rosa para nombres */
  font-weight: bold;
}

.cliente-item .email,
.cliente-item .telefono,
.cliente-item .direccion {
  color: #00d4ff; /* Azul claro para detalles */
}

.cliente-item:last-child {
  border-bottom: none; /* Sin línea de separación en el último cliente */
}
</style>
