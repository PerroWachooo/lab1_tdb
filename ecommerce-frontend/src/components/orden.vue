<template>
  <v-card class="detalle-card vaporwave-card" style="min-height: 300px;">
    <v-card-title class="detalle-card-title vaporwave-title">{{ orden.idOrden }}</v-card-title>
    <v-card-subtitle class="vaporwave-subtitle"> 
      Cliente: {{ orden.cliente ? orden.cliente.nombre : 'Sin cliente' }}
    </v-card-subtitle>
    <v-card-text class="vaporwave-text">
      Fecha: {{ orden.fechaOrden }}<br />
      Estado: {{ orden.estado }}<br />
      Total: ${{ orden.total }}
    </v-card-text>
    <v-card-title class="detalle-card-title vaporwave-title">
  {{ orden.idOrden }}
  <v-card-actions>
  <v-btn
    class="v-btn-card-action vaporwave-btn vaporwave-btn-delete"
    @click="eliminarOrden"
    style="background-color: #5e17eb; color: #ffffff; width: 100px;"
  >
    Eliminar
  </v-btn>
</v-card-actions>

</v-card-title>


  </v-card>
</template>




<script>
export default {
  props: {
    orden: {
      type: Object,
      required: true,
    },
  },
  methods: {
    

    eliminarOrden() {
      // Añadimos un mensaje de confirmación antes de eliminar
      if (!confirm("¿Estás seguro de que deseas eliminar esta orden?")) {
        return;
      }

      const ordenId = this.orden.idOrden; // Ajusta el nombre del campo si es diferente

      if (!ordenId) {
        console.error("El ID de la orden no está definido");
        alert("Error: No se puede eliminar la orden debido a que el ID no está definido.");
        return;
      }

      // Llamada DELETE al backend para eliminar la orden
      fetch(`http://localhost:8090/api/orden/delete-orden/${ordenId}`, {
        method: "DELETE",
      })
        .then((response) => {
          if (response.ok) {
            this.$emit("orden-eliminada", ordenId); // Emitir un evento para que el componente padre actualice la lista
            alert("Orden eliminada exitosamente");
          } else {
            alert("Error al eliminar la orden");
          }
        })
        .catch((error) => {
          console.error("Error:", error);
          alert("Ocurrió un error al eliminar la orden");
        });
    },
  },
};
</script>


<style scoped>


.detalle-card:hover {
  transform: scale(1.05); /* Aumenta ligeramente el tamaño al pasar el ratón */
  box-shadow: 0px 8px 25px rgba(0, 0, 0, 0.8); /* Mayor sombra al hacer hover */
}

/* Título de la tarjeta con estilo vaporwave */
.detalle-card-title.vaporwave-title {
  font-weight: bold; /* Negrita para resaltar el título */
  color: #ff71ce; /* Color rosa brillante */
  text-shadow: 0px 0px 8px rgba(255, 113, 206, 1); /* Sombra suave para dar profundidad y efecto neon */
  font-size: 1.5rem; /* Tamaño del título */
  padding: 10px 15px; /* Espaciado interno */
}

/* Estilo del subtítulo de la tarjeta */
.vaporwave-subtitle {
  font-size: 1rem; /* Tamaño más pequeño para el subtítulo */
  color: #d4f5ff; /* Azul claro estilo vaporwave */
  margin: 0 15px; /* Margen lateral */
  text-shadow: 0px 0px 6px rgba(212, 245, 255, 0.8); /* Sombra para dar el efecto de brillo */
}

/* Texto de la tarjeta */
.vaporwave-text {
  font-size: 1rem; /* Texto normal */
  color: #ffffff; /* Texto blanco */
  padding: 10px 15px; /* Espaciado interno */
  text-shadow: 0px 0px 4px rgba(255, 255, 255, 0.7); /* Efecto de brillo para el texto */
}

/* Botones dentro de la tarjeta */
.v-card-actions {
  display: flex; /* Flex para alinear los botones */
  justify-content: flex-end; /* Alinear los botones a la derecha */
  padding: 10px 15px; /* Espaciado interno */
}

.v-btn-card-action.vaporwave-btn {
  background-color: #00e5ff; /* Azul neón para el fondo del botón */
  color: #0e0732; /* Texto oscuro para contraste */
  font-weight: bold; /* Negrita para enfatizar */
  transition: background-color 0.3s ease; /* Transición suave para el color de fondo */
  border-radius: 8px; /* Bordes redondeados para hacer el botón más amigable */
}

.v-btn-card-action.vaporwave-btn:hover {
  background-color: #ff71ce; /* Cambio de color al pasar el ratón, consistente con el tema vaporwave */
  color: #ffffff; /* Asegurar que el color del texto siga siendo blanco */
}
</style>
