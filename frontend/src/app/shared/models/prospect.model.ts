import { User } from "./user.model";

export interface Prospect {
  _id?: string;
  firstName: string;
  lastName: string;
  trigramme: string;
  email: string;
  phone: string;
  profil: string;
  dateContact?: Date;
  dateEntretien?: Date
  statusProspect?: string;
  bum?: User;
  rh?: User;
  source?: string;
  prententionSalarial?: number;
  niveauEtude?: string;
  disponibilite?: string;
  mobiliteGeo?: string;
  cv?: string;
  grille?: string;
  pr?: number;
  dc?: string;
  pushQualif?: PushQualif;
}

export interface PushQualif {
  client: string;
  datePush: Date;
  datePushRetour: Date;
  dateQualif: Date;
}
