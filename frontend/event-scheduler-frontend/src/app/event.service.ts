import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Event } from './Event.model'; // Adjust the import path as necessary

@Injectable({ providedIn: 'root' })
export class EventService {
  private api = '/api/events';

  constructor(private http: HttpClient) {}

  getEvents(): Observable<Event[]> {
    return this.http.get<Event[]>(this.api);
  }

  createEvent(event: Event): Observable<Event> {
    return this.http.post<Event>(this.api, event);
  }

  updateEvent(event: Event): Observable<Event> {
    return this.http.put<Event>(`${this.api}/${event.id}`, event);
  }

  deleteEvent(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }

  summarize(description: string): Observable<{ summary: string }> {
    return this.http.post<{ summary: string }>(`${this.api}/summarize`, { description });
  }
}
