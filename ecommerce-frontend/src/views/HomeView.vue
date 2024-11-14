<script setup>
import { ref, onMounted } from 'vue';
import templatecard from '@/components/templatecard.vue';

const backgroundImageSrc = ref(''); // Imagen de fondo
const carouselImages = ref([]); // Array para almacenar las URLs de imágenes del carrusel
const model = ref(0); // Controlador para el carrusel

// Función para obtener una sola imagen para el fondo
async function fetchBackgroundImage(query) {
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
}

// Función para obtener varias imágenes para el carrusel
async function fetchCarouselImages(queries) {
  try {
    const promises = queries.map(async (query) => {
      const response = await fetch(
        `https://api.unsplash.com/search/photos?query=${query}&client_id=QkjMm1DzbXbkQDPZha7IrUSE_8UYBb-JHMrMbskJgis&per_page=1`
      );
      const data = await response.json();
      return data.results?.[0]?.urls.regular || 'https://example.com/default-carousel.jpg';
    });

    carouselImages.value = await Promise.all(promises);
    console.log(carouselImages.value); // Verificación de URLs de las imágenes
  } catch (error) {
    console.error('Error fetching carousel images:', error);
    carouselImages.value = ['https://example.com/default-carousel.jpg'];
  }
}

// Llamar a las funciones de fondo y carrusel cuando el componente se monte
onMounted(() => {
  fetchBackgroundImage('vaporwave'); // Ajusta el término de búsqueda para la imagen de fondo
  fetchCarouselImages(['cyberpunk','retrowave', 'synthwave', 'synth']); // Temas para las imágenes del carrusel
});
</script>
<template>
  <v-container class="landing-page" fluid fill-height>
    <!-- Adjust padding to push content down -->
    <v-parallax :src="backgroundImageSrc" height="auto" style="padding-top: 100px;">
      <v-row class="header" justify="center" align="center">
        <v-col cols="12" class="text-center">
          <h1 class="title">Ecommerce</h1>
          <h2 class="subtitle">Your One-Stop Retro Shop</h2>
        </v-col>
      </v-row>

      <!-- Carousel Section -->
      <v-row justify="center" class="carousel-section">
        <v-col cols="12">
          <div class="d-flex justify-space-around align-center py-4">
            <v-btn icon="mdi-minus" variant="text" @click="model.value = Math.max(model.value - 1, 0)"></v-btn>
            {{ model.value }}
            <v-btn icon="mdi-plus" variant="text" @click="model.value = Math.min(model.value + 1, carouselImages.length - 1)"></v-btn>
          </div>
          <v-carousel v-model="model" hide-delimiters>
            <v-carousel-item v-for="(image, i) in carouselImages" :key="i" :value="i">
              <v-sheet height="500px" tile class="carousel-item">
                <img :src="image" alt="Slide Image" class="carousel-image">
              </v-sheet>
            </v-carousel-item>
          </v-carousel>
        </v-col>
      </v-row>

      
      <templatecard
  :categoria="{ nombre: 'clientes' }"
  title="Clientes"
  description="Clientes are our core focus. Lorem ipsum dolor sit amet, consectetur adipiscing elit."
  position="left"
  imageQuery="business meeting"
/>

<templatecard
  :categoria="{ nombre: 'categorias' }"
  title="Categorias"
  description="Explore various categories. Lorem ipsum dolor sit amet, consectetur adipiscing elit."
  position="right"
  imageQuery="category icons"
/>

<templatecard
  :categoria="{ nombre: 'productos' }"
  title="Productos"
  description="Check out our products. Lorem ipsum dolor sit amet, consectetur adipiscing elit."
  position="left"
  imageQuery="product showcase"
/>

<templatecard
  :categoria="{ nombre: 'ordenes' }"
  title="Ordenes"
  description="Manage your orders efficiently. Lorem ipsum dolor sit amet, consectetur adipiscing elit."
  position="right"
  imageQuery="order management"
/>

<templatecard
  :categoria="{ nombre: 'detalleordenes' }"
  title="Detalle de Orden"
  description="View order details in depth. Lorem ipsum dolor sit amet, consectetur adipiscing elit."
  position="left"
  imageQuery="order details"
/>


    </v-parallax>
  </v-container>
</template>

<style scoped>
.landing-page {
  color: #ffffff;
  font-family: 'Poppins', sans-serif;
}

.header .title {
  font-size: 3rem;
  color: #ff6ec7;
  font-weight: 700;
  text-shadow: 0px 0px 8px rgba(255, 110, 199, 0.8);
}

.header .subtitle {
  font-size: 1.5rem;
  color: #fbd786;
  font-weight: 300;
  margin-top: 0.5rem;
  text-shadow: 0px 0px 4px rgba(251, 215, 134, 0.7);
}

.v-parallax__image-container {
  background-size: cover !important;
  background-position: center;
}

.carousel-section {
  padding-top: 20px;
}

.carousel-item {
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
}

.carousel-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.cards-section {
  padding-top: 30px;
}

.v-card {
  background: rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(10px);
  box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.5);
  border-radius: 8px;
  transition: transform 0.3s ease-in-out;
}

.v-card:hover {
  transform: scale(1.05);
}

.v-btn {
  color: #00d4ff;
  border-color: #00d4ff;
}

.v-btn:hover {
  background-color: #00d4ff;
  color: #ffffff;
}
</style>
