export type UserRole = 
  | 'ROLE_SUPER_ADMIN' 
  | 'ROLE_ADMIN' 
  | 'ROLE_TECHNICIAN' 
  | 'ROLE_CLIENT';

export interface User {
  id: string;
  username: string;
  email: string;
  fullName: string;
  roles: UserRole[];
}

export interface AuthResponse {
  token: string;
  type: string;
  id: string;
  username: string;
  email: string;
  fullName: string;
  roles: UserRole[];
}

export interface ApiMessageResponse {
  message: string;
  success: boolean;
}
