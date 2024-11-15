<template>
  <v-card
    :disabled="loading"
    :loading="loading"
    class="detalle-card mx-auto my-12"
    :max-width="maxWidth"
  >
    <template v-slot:loader="{ isActive }">
      <v-progress-linear
        :active="isActive"
        color="deep-purple"
        height="4"
        indeterminate
      ></v-progress-linear>
    </template>

    <v-card-item>
      <v-card-title class="detalle-card-title">{{ cliente.nombre }}</v-card-title>
      <v-card-subtitle>
        {{ cliente.email }}
      </v-card-subtitle>
    </v-card-item>

    <v-card-text>
      <p><strong>Dirección:</strong> {{ cliente.direccion }}</p>
      <p><strong>Teléfono:</strong> {{ cliente.telefono }}</p>
    </v-card-text>

    <v-card-actions>
      
      <v-btn
        class="v-btn-card-action delete-btn"
        text="Eliminar"
        block
        border
        @click="eliminar"
      >
        Eliminar
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  props: {
    cliente: {
      type: Object,
      required: true,
    },
    maxWidth: {
      type: [String, Number],
      default: 400,
    },
  },

  data() {
    return {
      loading: false,
    };
  },

  methods: {
   

    eliminar() {
  this.loading = true;

  // Verificar el contenido completo del cliente (para depuración)
  console.log("Objeto cliente:", this.cliente);

  const clienteId = this.cliente.id_cliente; // Cambiado de 'id' a 'id_cliente'

  // Verificar si el ID está definido
  if (!clienteId) {
    console.error("El ID del cliente no está definido");
    alert("Error: No se puede eliminar el cliente debido a que el ID no está definido.");
    this.loading = false;
    return;
  }

  console.log("Cliente ID a eliminar:", clienteId); // Log para verificar el valor del ID

  fetch(`http://localhost:8090/api/cliente/delete-cliente/${clienteId}`, {
    method: "DELETE",
  })
    .then((response) => {
      if (response.ok) {
        this.$emit("cliente-eliminado", clienteId); // Emitir un evento para que el componente padre actualice la lista
        alert("Cliente eliminado exitosamente");
      } else {
        alert("Error al eliminar el cliente");
      }
      this.loading = false;
    })
    .catch((error) => {
      console.error("Error:", error);
      alert("Ocurrió un error al eliminar el cliente");
      this.loading = false;
    });
}

  },
};
</script>

<style scoped>
/* Estilo para las Cards */

/* Estilo base de la tarjeta (detalle-card) */
.detalle-card {
  background: rgba(255, 255, 255, 0.8); /* Fondo semitransparente */
  color: #000; /* Texto en negro para buena legibilidad */
  box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.5); /* Sombra para efecto de elevación */
  transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out; /* Transición suave */
  border-radius: 10px; /* Bordes redondeados */
  overflow: hidden; /* Para evitar que los elementos internos se salgan */
}

.detalle-card:hover {
  transform: scale(1.05); /* Aumenta ligeramente el tamaño al pasar el ratón */
  box-shadow: 0px 8px 20px rgba(0, 0, 0, 0.7); /* Mayor sombra al hacer hover */
}

/* Título de la tarjeta */
.detalle-card-title {
  font-weight: bold; /* Negrita para resaltar el título */
  color: #ff6ec7; /* Color vibrante */
  text-shadow: 0px 0px 5px rgba(255, 110, 199, 0.8); /* Sombra suave para profundidad */
  font-size: 1.3rem; /* Tamaño del título */
  padding: 10px 15px; /* Espaciado interno */
}

/* Estilo del subtítulo de la tarjeta */
.v-card-subtitle {
  font-size: 1rem; /* Tamaño más pequeño para el subtítulo */
  color: #6b6b6b; /* Color apagado para el subtítulo */
  margin: 0 15px; /* Margen lateral */
}

/* Texto de la tarjeta */
.v-card-text {
  font-size: 1rem; /* Texto normal */
  color: #333; /* Asegura buena legibilidad */
  padding: 10px 15px; /* Espaciado interno */
}

/* Botones dentro de la tarjeta */
.v-card-actions {
  display: flex; /* Flex para alinear los botones */
  justify-content: flex-end; /* Alinear los botones a la derecha */
  padding: 10px 15px; /* Espaciado interno */
}

.v-btn-card-action {
  background-color: #ff6ec7; /* Color de fondo del botón */
  color: #ffffff; /* Texto blanco */
  font-weight: bold; /* Negrita */
  transition: background-color 0.3s ease; /* Transición suave para el color de fondo */
}

.v-btn-card-action:hover {
  background-color: #00d4ff; /* Cambio de color al hacer hover */
  color: #ffffff; /* Asegurar que el color del texto siga siendo blanco */
}

/* Espaciado general para los párrafos dentro de la tarjeta */
p {
  margin: 0; /* Eliminar margen por defecto para los párrafos */
}
</style>
