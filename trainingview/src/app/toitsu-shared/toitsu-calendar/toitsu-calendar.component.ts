import {AfterViewInit, Component, ElementRef, EventEmitter, Input, OnInit, Optional, Output, Renderer2, SkipSelf} from '@angular/core';
import {ControlContainer} from '@angular/forms';

@Component({
  selector: 'app-toitsu-calendar',
  templateUrl: './toitsu-calendar.component.html',
  viewProviders: [
    {
      provide: ControlContainer,
      useFactory: (container: ControlContainer) => container,
      deps: [[new Optional(), new SkipSelf(), ControlContainer]]
    }
  ]
})
export class ToitsuCalendarComponent implements OnInit, AfterViewInit {
  
  @Input() controlName: string;
  @Input() model: Date;
  @Output() modelChange = new EventEmitter<Date>();
  
  @Input() showTime = false;
  @Input() noIcon = false;
  @Input() inputId: string;
  @Input() disabled = false;
  
  localeEl: any;
  
  constructor(private renderer: Renderer2, private elementRef: ElementRef) {}
  
  ngOnInit() {
  }
  
  ngAfterViewInit() {
    let button = this.elementRef.nativeElement.querySelector('button');
    this.renderer.addClass(button, 'p-button-info');
  }
  
  emitModelChange() {
    this.modelChange.emit(this.model);
  }
}
