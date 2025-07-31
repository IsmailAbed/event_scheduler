export interface Event {
  id?: number;
  title: string;
  description: string;
  location: string;
  dateTime: string; // ISO string
  status: 'upcoming' | 'attending' | 'maybe' | 'declined';
}