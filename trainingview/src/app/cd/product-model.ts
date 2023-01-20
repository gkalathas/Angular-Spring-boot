import {RatingModel} from './rating-model';

export class ProductModel {

  id: number;
  title: string;

  price: number;

  description: string;

  pcategory: string;

  image: string;

  rating: RatingModel;
}
