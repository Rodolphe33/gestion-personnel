import { Role } from "./role.model";

export interface User {
  _id: string;
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  roles: [Role];
  authorities?: [];
  username?: string;
}
