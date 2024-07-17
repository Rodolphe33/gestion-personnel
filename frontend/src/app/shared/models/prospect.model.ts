import { FileStorage } from "./fileStorage";
import { User } from "./user.model";

export interface Personnal {
  _id?: string;
  firstName: string;
  lastName: string;
  trigramme: string;
  email: string;
  phone: string;
  profil: string;
  dateContact?: Date;
  dateEntretien?: Date
  statusPersonnal?: string;
  bum?: User | string;
  rh?: User | string;
  source?: string;
  prententionSalarial?: number;
  niveauEtude?: string;
  disponibilite?: string;
  mobiliteGeo?: string;
  goNogo?: boolean;
  cv?: FileStorage | string;
  grille?: FileStorage | string;
  pr?: number;
  dc?: FileStorage | string;
  pushQualif?: PushQualif;
}

export interface PushQualif {
  client: string;
  datePush: Date;
  datePushRetour: Date;
  dateQualif: Date;
}

export interface newPersonnal {
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
  profil: string;
  rh: string | User | undefined;
  trigramme: string;
  cv: string;
}
