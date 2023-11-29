export interface AuthenticationRequest {
  email: string;
  password: string;
}

export interface AuthenticationResponse {
  token: string;
  role: string;
}

export interface UserDTO {
  id: number;
  name: string;
  email: string;
  password: string;
  admin: boolean;
}

export interface RatingDTO {
  id: number;
  value: number;
  comment: string;
}
export interface AgentDTO {
  id: number;
  name: string;
  email: string;
  licenseNumber: string;
}

export interface DealDTO {
  id: number|null;
  buyer: UserDTO|null;
  property: PropertyDTO;
  status: string;
}



export interface PropertyDTO {
  id: number;
  address: string;
  price: number;
  description: string;
  available: boolean;
  agent: AgentDTO;
}

export interface RatingDTO {
  id: number;
  value: number;
  comment: string;
}
