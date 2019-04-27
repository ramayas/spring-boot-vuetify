// @ts-ignore
import { faculty } from './faculty'

/**
 * Class to represent teacher
 */
export class teacher {
    id!:number;
    name!:string;
    email!:string;
    private faculty!: faculty;
}
