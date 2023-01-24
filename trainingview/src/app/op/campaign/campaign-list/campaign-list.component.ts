import {Component, OnInit, ViewChild} from '@angular/core';
import {ToitsuTableComponent} from '../../../toitsu-shared/toitsu-table/toitsu-table.component';
import {TranslateService} from '@ngx-translate/core';
import {DialogService} from 'primeng/dynamicdialog';

@Component({
  selector: 'app-campaign-list',
  templateUrl: './campaign-list.component.html',
  styleUrls: ['./campaign-list.component.scss']
})
export class CampaignListComponent implements OnInit {

  constructor(private translateService: TranslateService,
              private dialogService: DialogService) { }


  @ViewChild(ToitsuTableComponent) table;

  // const args = {}
  url = 'trainingapi/trn/campaigns/index';
  args = this.initializeArgs();
  first: any;
  rows: any;
  sortField: any;
  sortOrder: any;
  viewLink = '/trn/campaigns/index';

  cols = [
    {
      field: 'id',
      header: this.translateService.instant('id'),
      sortField: 'id',
      width: 20,
      align: 'center'
    },
    {
      field: 'name',
      header: this.translateService.instant('name'),
      sortField: 'name',
      width: 20,
      align: 'center'
    },
    {
      field: 'campaignTypeId',
      header: this.translateService.instant('campaignTypeId'),
      sortField: 'campaignTypeId',
      width: 20,
      align: 'center'
    },
    {
      field: 'cost',
      header: this.translateService.instant('cost'),
      sortField: 'cost',
      width: 20,
      align: 'center'
    },
    {
      field: 'isRunning',
      header: this.translateService.instant('isRunning'),
      sortField: 'isRunning',
      width: 20,
      align: 'center'
    },
    {
      field: 'startDate',
      header: this.translateService.instant('startDate'),
      sortField: 'startDate',
      width: 20,
      align: 'center'
    },
    {
      field: 'endDate',
      header: this.translateService.instant('endDate'),
      sortField: 'endDate',
      width: 20,
      align: 'center'
    },
    {
      field: 'comments',
      header: this.translateService.instant('comments'),
      sortField: 'comments',
      width: 20,
      align: 'center'
    }
  ];

  ngOnInit(): void {
  }

  loadComplete($event: {}) {
    
  }

  loadError($event: {}) {
    
  }

  initializeArgs() {
    return {
      name: null,
      campaignTypeId: null,
      isRunning: null,
      startDateAfter: null,
      startDateBefore: null
    };
  }
}
