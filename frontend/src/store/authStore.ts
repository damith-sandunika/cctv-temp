import { create } from 'zustand';
import type { User, AuthResponse, UserRole } from '../types/auth';

interface AuthState {
  user: User | null;
  token: string | null;
  isAuthenticated: boolean;
  setAuth: (response: AuthResponse) => void;
  logout: () => void;
  hasRole: (role: UserRole) => boolean;
  getPrimaryRole: () => UserRole | null;
  getDefaultDashboardPath: () => string;
}

export const useAuthStore = create<AuthState>((set, get) => {
  const savedToken = localStorage.getItem('lumina_token');
  const savedUser = localStorage.getItem('lumina_user');

  return {
    token: savedToken || null,
    user: savedUser ? JSON.parse(savedUser) : null,
    isAuthenticated: !!savedToken,

    setAuth: (response: AuthResponse) => {
      const user: User = {
        id: response.id,
        username: response.username,
        email: response.email,
        fullName: response.fullName,
        roles: response.roles,
      };

      localStorage.setItem('lumina_token', response.token);
      localStorage.setItem('lumina_user', JSON.stringify(user));

      set({
        token: response.token,
        user,
        isAuthenticated: true,
      });
    },

    logout: () => {
      localStorage.removeItem('lumina_token');
      localStorage.removeItem('lumina_user');
      set({
        token: null,
        user: null,
        isAuthenticated: false,
      });
    },

    hasRole: (role: UserRole) => {
      const { user } = get();
      return !!user?.roles?.includes(role);
    },

    getPrimaryRole: () => {
      const { user } = get();
      if (!user || !user.roles.length) return null;
      if (user.roles.includes('ROLE_SUPER_ADMIN')) return 'ROLE_SUPER_ADMIN';
      if (user.roles.includes('ROLE_ADMIN')) return 'ROLE_ADMIN';
      if (user.roles.includes('ROLE_TECHNICIAN')) return 'ROLE_TECHNICIAN';
      if (user.roles.includes('ROLE_CLIENT')) return 'ROLE_CLIENT';
      return user.roles[0];
    },

    getDefaultDashboardPath: () => {
      const primaryRole = get().getPrimaryRole();
      switch (primaryRole) {
        case 'ROLE_SUPER_ADMIN':
          return '/super-admin/dashboard';
        case 'ROLE_ADMIN':
          return '/admin/dashboard';
        case 'ROLE_TECHNICIAN':
          return '/technician/dashboard';
        case 'ROLE_CLIENT':
          return '/client/dashboard';
        default:
          return '/login';
      }
    },
  };
});
