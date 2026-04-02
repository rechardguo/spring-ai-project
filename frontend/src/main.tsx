import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import AppSSE from "./AppSSE.tsx";

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <AppSSE />
  </StrictMode>,
)
