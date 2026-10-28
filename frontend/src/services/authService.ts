import api from './api';
import type { AuthResponse } from '../types/auth';

export interface LoginPayload {
  username: string;
  password: string;
}

export const authService = {
  login: async (credentials: LoginPayload): Promise<AuthResponse> => {
    const response = await api.post<AuthResponse>('/auth/signin', credentials);
    return response.data;
  },

  checkHealth: async () => {
    const response = await api.get('/health');
    return response.data;
  },
};
