import { defineStore } from 'pinia';
import { router } from '@/router';

const baseUrl = `${import.meta.env.VITE_API_URL}/users`;

export const useAuthStore = defineStore({
    id: 'auth',
    state: () => ({
        // initialize state from local storage to enable user to stay logged in
        user: JSON.parse(localStorage.getItem('user')),
        returnUrl: null
    }),
    actions: {
        async login(username, password) {
            try {
                // const user = await fetchWrapper.post(`${baseUrl}/authenticate`, { username, password });
                const user = { id: 1, username, password };

                // update pinia state
                this.user = user;

                // store user details and jwt in local storage to keep user logged in between page refreshes
                localStorage.setItem('user', JSON.stringify(user));

                // redirect to previous url or default to home page
                router.push('/panels');
            } catch (error) {
                const alertStore = useAlertStore();
                alertStore.error(error);                
            }
        },
        logout() {
            this.user = null;
            localStorage.removeItem('user');
            router.push('/');
        }
    }
});