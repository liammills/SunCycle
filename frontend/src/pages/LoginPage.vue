<template>
  <q-page class="flex flex-center">
    <ResetPasswordForm
      v-if="resetForm"
      v-model:email="email"
      v-model:error="error"
      @toggle-reset="toggleReset()"
      @reset="resetPassword()"
    />
    <UserForm
      v-else
      title="Login"
      submit-text="Login"
      v-model:email="email"
      v-model:password="password"
      v-model:error="error"
      @toggle-reset="toggleReset()"
      @submit="submit()"
    />
  </q-page>
</template>

<script>
import UserForm from '../components/UserForm.vue';
import ResetPasswordForm from '../components/ResetPasswordForm.vue';
import { useAuthStore } from 'stores/auth';

export default {
  name: 'LoginPage',
  setup() {
    const authStore = useAuthStore();
    return {
      authStore,
    };
  },
  components: {
    UserForm,
    ResetPasswordForm,
  },
  data() {
    return {
      resetForm: false,
      email: null,
      password: null,
      error: null,
    };
  },
  computed: {
    areFieldsValid() {
      return /^(\S+@\S+\.\S+|\d{10}|\d{11})$/.test(this.email);
    },
  },
  methods: {
    toggleReset() {
      this.error = null;
      this.resetForm = !this.resetForm;
    },
    async submit() {
      if (!this.areFieldsValid) {
        this.error = 'Please enter a valid email';
        return;
      }
      this.error = null;
      try {
        const result = await this.authStore.login({
          email: this.email,
          password: this.password,
        });
        if (!result || result.status !== 200) {
          this.password = '';
          this.error = 'Invalid login. Please try again';
          return;
        }
        else {
          this.$router.push('/panels');
        }
      } catch (error) {
        this.password = '';
        if (error.response?.data?.message) {
          this.error = error.response.data.message;
        }
      }
    },
    async resetPassword() {
      // TODO

      // try {
      //   this.error = null;
      //   await this.$api.post('users/reset_password',
      //     {
      //       email: this.email,
      //     });
      //   this.toggleReset();
      //   this.error = 'Password reset email sent, please check your email';
      // } catch (error) {
      //   this.error = 'Error resetting password';
      //   if (error.response?.data?.message) {
      //     this.error = error.response.data.message;
      //   }
      // }
    },
  },
}
</script>
