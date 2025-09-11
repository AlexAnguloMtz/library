import { UserFactory } from "../factories/UserFactory";
import type { User } from "../models/User";

interface UserClient {
    getUsers(): string;
}

export interface UserService {
    getUsers(): Promise<User[]>;
}

class DevelopmentUserService implements UserService {
    async getUsers(): Promise<User[]> {
        await new Promise(resolve => setTimeout(resolve, 3000));
        return UserFactory.createUsers(25);
    }
}

class ProductionUserService implements UserService {
    private userClient: UserClient;

    constructor() {
        // @ts-ignore
        if (!window.userClient) {
            throw new Error("user client not available in window");
        }

        // @ts-ignore
        this.userClient = window.userClient;
    }

    async getUsers(): Promise<User[]> {
        await new Promise(resolve => setTimeout(resolve, 1000));
        return JSON.parse(this.userClient.getUsers()) as User[];
    }

}

const isDevelopment = process.env.NODE_ENV === 'development';

export const userService = isDevelopment
    ? new DevelopmentUserService()
    : new ProductionUserService();
