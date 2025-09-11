import { UserFactory } from "../factories/UserFactory";
import type { UserPreview } from "../models/UserPreview";

interface UserClient {
    getUsersPreviews(): string;
}

export interface UserService {
    getUsersPreviews(): Promise<UserPreview[]>;
}

class DevelopmentUserService implements UserService {
    async getUsersPreviews(): Promise<UserPreview[]> {
        await new Promise(resolve => setTimeout(resolve, 3000));
        return UserFactory.createUsersPreviews(25);
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

    async getUsersPreviews(): Promise<UserPreview[]> {
        await new Promise(resolve => setTimeout(resolve, 1000));
        return JSON.parse(this.userClient.getUsersPreviews()) as UserPreview[];
    }

}

const isDevelopment = process.env.NODE_ENV === 'development';

export const userService = isDevelopment
    ? new DevelopmentUserService()
    : new ProductionUserService();
