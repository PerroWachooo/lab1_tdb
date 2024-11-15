<template>
  <div>
    <v-container>
      <v-row>
        <v-col v-for="producto in productos" :key="producto.idProducto" cols="12" md="4">
          <v-card class="detalle-card">
            <v-card-title class="detalle-card-title">{{ producto.nombre }}</v-card-title>
            <v-card-subtitle>
              Categoría: {{ producto.categoria ? producto.categoria.nombre : 'Sin categoría' }}
            </v-card-subtitle>
            <v-card-text>
              Descripción: {{ producto.descripcion }}<br />
              Precio: ${{ producto.precio }}<br />
              Stock: {{ producto.stock }}<br />
              Estado: {{ producto.estado }}
            </v-card-text>
            <v-card-actions>
              <v-btn class="v-btn-card-action" text @click="actualizarProducto(producto)">Actualizar</v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      productos: [],
    };
  },
  mounted() {
    this.fetchProductos();
  },
  methods: {
    async fetchProductos() {
      try {
        const response = await axios.get('http://localhost:8090/api/productos/');
        this.productos = response.data;

        // Iterar sobre cada producto para obtener la información de la categoría
        await Promise.all(
          this.productos.map(async (producto) => {
            if (producto.id_categoria) {
              try {
                const categoriaResponse = await axios.get(
                  `http://localhost:8090/api/categorias/id-categoria/${producto.id_categoria}`
                );
                producto.categoria = categoriaResponse.data; // Asignar la categoría al producto
              } catch (error) {
                console.error(`Error al obtener la categoría del producto ${producto.idProducto}:`, error);
              }
            }
          })
        );
      } catch (error) {
        console.error('Error al obtener los productos:', error);
      }
    },
    actualizarProducto(producto) {
      // Lógica para actualizar el producto (opcional)
      console.log('Actualizar producto:', producto);
    },
  },
};
</script>

<style scoped>
/* Estilos de las Cards */

/* Detalle Card Styles */
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

/* Detalle Card Title Styles */
.detalle-card-title {
  font-weight: bold; /* Negrita para destacar el título */
  color: #ff6ec7; /* Color vibrante que contrasta con el fondo */
  text-shadow: 0px 0px 5px rgba(255, 110, 199, 0.8); /* Sombra para dar profundidad al título */
  font-size: 1.3rem; /* Tamaño adecuado para que destaque */
  padding: 10px 15px; /* Espaciado alrededor del título */
}

/* Card Subtitle Styles */
.v-card-subtitle {
  font-size: 1rem; /* Tamaño del subtítulo más pequeño que el título */
  color: #6b6b6b; /* Un color más apagado para el subtítulo */
  margin: 0 15px; /* Espaciado lateral para alinear con el título */
}

/* Card Text Styles */
.v-card-text {
  font-size: 1rem; /* Texto normal para el contenido principal */
  color: #333; /* Color de texto que asegura buena legibilidad */
  padding: 10px 15px; /* Espaciado interno para el texto */
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

/* Añadir espaciado general para que las cards no se vean tan pegadas */
.v-col {
  margin-bottom: 20px;
}
</style>
