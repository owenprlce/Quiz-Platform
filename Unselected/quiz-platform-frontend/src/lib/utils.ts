import { type ClassValue, clsx } from "clsx"
import { twMerge } from "tailwind-merge"

export function cntw(...inputs: ClassValue[]) {
    return twMerge(clsx(inputs))
}