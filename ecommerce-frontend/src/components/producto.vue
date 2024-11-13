<template>
  <div>
    <v-container>
      <v-row>
        <v-col v-for="producto in productos" :key="producto.idProducto" cols="12" md="4">
          <v-card>
            <v-card-title>{{ producto.nombre }}</v-card-title>
            <v-card-subtitle>
              Categoría: {{ producto.categoria ? producto.categoria.nombre : 'Sin categoría' }}
            </v-card-subtitle>
            <v-card-text>
              Descripción: {{ producto.descripcion }}<br />
              Precio: {{ producto.precio }}<br />
              Stock: {{ producto.stock }}<br />
              Estado: {{ producto.estado }}
            </v-card-text>
            <v-card-actions>
              <v-btn text @click="actualizarProducto(producto)">Actualizar</v-btn>
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
