<template>
    <v-card
      :disabled="loading"
      :loading="loading"
      class="mx-auto my-12"
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
  
      <v-img
        :height="imageHeight"
        :src="imageSrc"
        cover
      ></v-img>
  
      <v-card-item>
        <v-card-title>{{ title }}</v-card-title>
        <v-card-subtitle>
          <span class="me-1">{{ subtitle }}</span>
          <v-icon
            color="error"
            icon="mdi-fire-circle"
            size="small"
          ></v-icon>
        </v-card-subtitle>
      </v-card-item>
  
      <v-card-text>
  
        <div>{{ description }}</div>
      </v-card-text>
  
      <v-divider class="mx-4 mb-1"></v-divider>
  
      <v-card-title>Filtrar por</v-card-title>
  
      <div class="px-4 mb-2">
        <v-chip-group v-model="selection" selected-class="bg-deep-purple-lighten-2">
          <v-chip v-for="(filtro, index) in filtro" :key="index">{{ filtro }}</v-chip>
        </v-chip-group>
      </div>
  
      <v-card-actions>
        <v-btn
          color="deep-purple-lighten-2"
          text="Reserve"
          block
          border
          @click="reserve"
        >
          Buscar
        </v-btn>
      </v-card-actions>
    </v-card>
  </template>
  
  <script>
  export default {
    props: {
      title: {
        type: String,
        default: "Cafe Badilico",
      },
      subtitle: {
        type: String,
        default: "Local Favorite",
      },
      imageSrc: {
        type: String,
        default: "https://cdn.vuetifyjs.com/images/cards/cooking.png",
      },
      imageHeight: {
        type: [String, Number],
        default: 250,
      },
      description: {
        type: String,
        default:
          "Small plates, salads & sandwiches - an intimate setting with 12 indoor seats plus patio seating.",
      },
      filtro: {
        type: Array,
        default: () => ["5:30PM", "7:30PM", "8:00PM", "9:00PM"],
      },
      maxWidth: {
        type: [String, Number],
        default: 374,
      },
    },
  
    data: () => ({
      loading: false,
      selection: 1,
    }),
  
    methods: {
      reserve() {
        this.loading = true;
  
        setTimeout(() => (this.loading = false), 2000);
      },
    },
  };
  </script>
  