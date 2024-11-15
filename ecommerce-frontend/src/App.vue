<!-- src/App.vue -->
<template>
    <v-app>
      <!-- Global Navbar Component -->
      <Navbar />
  
      <!-- Router View for Page Content -->
      <router-view />

      
    </v-app>
  </template>
  
  <script setup>
  import Navbar from './components/navbar.vue';
  </script>
  
  <style>
  /* Add any global styles if needed */
  </style>
  