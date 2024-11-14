<template>
  <v-card
    :disabled="loading"
    :loading="loading"
    class="mx-auto my-12 full-width-card"
    max-width="100%"
    outlined
  >
    <template v-slot:loader="{ isActive }">
      <v-progress-linear
        :active="isActive"
        color="deep-purple"
        height="4"
        indeterminate
      ></v-progress-linear>
    </template>

    <v-row no-gutters>
      <!-- Conditionally place image on left or right -->
      <v-col v-if="position === 'left'" :cols="imageColWidth">
        <v-img
          :height="imageHeight"
          :src="imageSrc"
          class="rounded-l-lg"
          cover
        ></v-img>
      </v-col>

      <v-col :cols="contentColWidth" class="p-6 text-overlay">
        <div class="text-background">
          <v-card-title>{{ title }}</v-card-title>
          <v-card-subtitle class="text--primary">{{ categoria.nombre }}</v-card-subtitle>
          <v-card-text class="text-body-1">
            {{ description }}
          </v-card-text>
          <v-divider class="my-4"></v-divider>
          <v-card-actions>
            <v-btn
              color="deep-purple lighten-2"
              block
              @click="buscar"
            >
              See More
            </v-btn>
          </v-card-actions>
        </div>
      </v-col>

      <v-col v-if="position === 'right'" :cols="imageColWidth">
        <v-img
          :height="imageHeight"
          :src="imageSrc"
          class="rounded-r-lg"
          cover
        ></v-img>
      </v-col>
    </v-row>
  </v-card>
</template>

<script>
export default {
  props: {
    categoria: {
      type: Object,
      required: true,
    },
    title: {
      type: String,
      default: '',
    },
    description: {
      type: String,
      default: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.',
    },
    position: {
      type: String,
      default: 'left', // 'left' or 'right' for image position
    },
    imageQuery: {
      type: String,
      default: '', // Término de búsqueda para la imagen
    },
    imageHeight: {
      type: [String, Number],
      default: 300,
    },
    imageColWidth: {
      type: [String, Number],
      default: 5,
    },
    contentColWidth: {
      type: [String, Number],
      default: 7,
    },
  },

  data() {
    return {
      loading: false,
      imageSrc: '', // Imagen inicial vacía
    };
  },

  watch: {
    imageQuery: {
      immediate: true,
      handler(newQuery) {
        this.fetchImage(newQuery || this.categoria.nombre);
      },
    },
  },

  methods: {
    buscar() {
      const routeName = this.categoria.nombre.toLowerCase();
      this.$router.push({ name: routeName });
    },

    async fetchImage(query) {
      try {
        const response = await fetch(
          `https://api.unsplash.com/search/photos?query=${query}&client_id=QkjMm1DzbXbkQDPZha7IrUSE_8UYBb-JHMrMbskJgis&per_page=1`
        );
        const data = await response.json();
        this.imageSrc = data.results?.[0]?.urls?.small || 'https://example.com/default.jpg';
      } catch (error) {
        console.error('Error fetching image:', error);
        this.imageSrc = 'https://example.com/default.jpg';
      }
    },
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap');

.full-width-card {
  width: 100%;
  border-radius: 12px;
}

.v-img {
  border-radius: 12px;
}

.text-overlay {
  display: flex;
  align-items: center;
  justify-content: center;
}

.text-background {
  background-color: rgba(0, 0, 0, 0.6); /* Semi-transparent background */
  padding: 20px;
  border-radius: 8px;
  color: #ffffff;
  font-family: 'Poppins', sans-serif; /* Futuristic font */
  text-shadow: 1px 1px 4px rgba(0, 0, 0, 0.6); /* Text shadow for readability */
}

.v-card-title {
  font-size: 1.8rem;
  font-weight: 600;
  color: #E0E0E0; /* Lighter color for title */
}

.v-card-subtitle {
  color: #A3A3FF; /* Slightly lighter purple for subtitle */
  font-size: 1.2rem;
}

.v-card-text {
  color: #f0f0f0;
  font-size: 1rem;
  line-height: 1.6;
}

.v-card-actions {
  padding: 0;
}
</style>
