import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule, DatePipe } from '@angular/common';
import { EventService } from './event.service';
import { Event } from './Event.model';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  providers: [DatePipe]
})
export class AppComponent implements OnInit {
  events: Event[] = [];
  event: Event = {
    title: '',
    description: '',
    location: '',
    dateTime: new Date().toISOString(),
    status: 'upcoming'
  };
  summary = '';

  constructor(private eventService: EventService) {}

  ngOnInit(): void {
    this.loadEvents();
  }

  loadEvents(): void {
    this.eventService.getEvents().subscribe(data => this.events = data);
  }

  saveEvent(): void {
    if (this.event.id) {
      this.eventService.updateEvent(this.event).subscribe(() => this.loadEvents());
    } else {
      this.eventService.createEvent(this.event).subscribe(() => this.loadEvents());
    }
    this.resetForm();
  }

  editEvent(event: Event): void {
    this.event = { ...event };
    this.summary = '';
  }

  deleteEvent(id: number): void {
    this.eventService.deleteEvent(id).subscribe(() => this.loadEvents());
  }

  summarize(): void {
    this.eventService.summarize(this.event.description).subscribe(res => {
      this.summary = res.summary;
    });
  }

  resetForm(): void {
    this.event = {
      title: '',
      description: '',
      location: '',
      dateTime: new Date().toISOString(),
      status: 'upcoming'
    };
    this.summary = '';
  }
}
