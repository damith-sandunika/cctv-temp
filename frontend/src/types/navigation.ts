import type { ComponentType } from 'react';
import type { UserRole } from './auth';

export interface NavItem {
  id: string;
  title: string;
  path: string;
  icon: ComponentType<{ className?: string; size?: number; strokeWidth?: number }>;
  badge?: string;
  isComingSoon?: boolean;
}

export interface RoleNavigation {
  role: UserRole;
  roleName: string;
  basePath: string;
  items: NavItem[];
}
