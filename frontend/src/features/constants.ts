export const backendUri = 'http://localhost:8080'

export interface LoginResponse {
    personId: string;
    token: string;
}

export const localStorageGetBoolean = (name: string) => {
    const value: string = localStorage.getItem(name) ?? "false"
    return value === "true"
}
