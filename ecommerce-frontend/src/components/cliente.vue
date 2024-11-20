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
      <v-card-title class="detalle-card-title">
        <v-text-field
          v-if="isEditing"
          v-model="editableCliente.nombre"
          label="Nombre"
          outlined
          dense
        />
        <span v-else>{{ cliente.nombre }}</span>
      </v-card-title>
      <v-card-subtitle>
        <v-text-field
          v-if="isEditing"
          v-model="editableCliente.email"
          label="Email"
          outlined
          dense
        />
        <span v-else>{{ cliente.email }}</span>
      </v-card-subtitle>
    </v-card-item>

    <v-card-text>
      <div>
        <strong>Dirección:</strong>
        <v-text-field
          v-if="isEditing"
          v-model="editableCliente.direccion"
          label="Dirección"
          outlined
          dense
        />
        <span v-else>{{ cliente.direccion }}</span>
      </div>
      <div>
        <strong>Teléfono:</strong>
        <v-text-field
          v-if="isEditing"
          v-model="editableCliente.telefono"
          label="Teléfono"
          outlined
          dense
        />
        <span v-else>{{ cliente.telefono }}</span>
      </div>
    </v-card-text>

    <v-card-actions>
      <!-- Botón para editar -->
      <v-btn
        v-if="!isEditing"
        class="v-btn-card-action edit-btn"
        text="Editar"
        block
        border
        @click="isEditing = true"
      >
        Editar
      </v-btn>

      <!-- Botones para guardar o cancelar la edición -->
      <v-btn
        v-if="isEditing"
        class="v-btn-card-action save-btn"
        text="Guardar"
        block
        border
        @click="guardar"
      >
        Guardar
      </v-btn>

      <v-btn
        v-if="isEditing"
        class="v-btn-card-action cancel-btn"
        text="Cancelar"
        block
        border
        @click="cancelar"
      >
        Cancelar
      </v-btn>

      <!-- Botón para eliminar -->
      <v-btn
        v-if="!isEditing"
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
      isEditing: false,
      editableCliente: { ...this.cliente }, // Clonamos el cliente para evitar modificar directamente el original
    };
  },

  watch: {
    cliente: {
      handler(newValue) {
        this.editableCliente = { ...newValue };
      },
      immediate: true,
    },
  },

  methods: {
    // Método para eliminar el cliente
    eliminar() {
      const clienteId = this.cliente.id_cliente;
      if (clienteId) {
        this.$emit("eliminar", clienteId);
      }
    },

    // Método para guardar los cambios
    guardar() {
      this.$emit("guardar", this.editableCliente);
      this.isEditing = false;
    },

    // Método para cancelar la edición
    cancelar() {
      this.isEditing = false;
      this.editableCliente = { ...this.cliente }; // Restaurar los valores originales
    },
  },
};
</script>

<style scoped>
/* Estilo base del componente Cliente.vue */
.detalle-card {
  background: rgba(255, 255, 255, 0.8);
  color: #000;
  box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.5);
  transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
  border-radius: 10px;
  overflow: hidden;
}

.detalle-card:hover {
  transform: scale(1.05);
  box-shadow: 0px 8px 20px rgba(0, 0, 0, 0.7);
}

.detalle-card-title {
  font-weight: bold;
  color: #ff6ec7;
  text-shadow: 0px 0px 5px rgba(255, 110, 199, 0.8);
  font-size: 1.3rem;
  padding: 10px 15px;
}

.v-card-subtitle {
  font-size: 1rem;
  color: #6b6b6b;
  margin: 0 15px;
}

.v-card-text {
  font-size: 1rem;
  color: #333;
  padding: 10px 15px;
}

.v-card-actions {
  display: flex;
  justify-content: space-between;
  padding: 10px 15px;
}

.v-btn-card-action {
  background-color: #ff6ec7;
  color: #ffffff;
  font-weight: bold;
  transition: background-color 0.3s ease;
}

.v-btn-card-action:hover {
  background-color: #00d4ff;
  color: #ffffff;
}
</style>
