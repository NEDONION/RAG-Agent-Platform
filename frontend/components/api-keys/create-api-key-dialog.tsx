"use client"

import { useState } from "react"
import { Copy, Eye, EyeOff } from "lucide-react"
import { Button } from "@/components/ui/button"
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
} from "@/components/ui/dialog"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select"
import { toast } from "@/hooks/use-toast"
import { ApiKeyResponse } from "@/types/api-key"
import type { Agent } from "@/types/agent"

interface CreateApiKeyDialogProps {
  open: boolean
  onOpenChange: (open: boolean) => void
  agents: Agent[]
  onCreateKey: (agentId: string, name: string) => Promise<ApiKeyResponse | null>
}

export function CreateApiKeyDialog({
  open,
  onOpenChange,
  agents,
  onCreateKey
}: CreateApiKeyDialogProps) {
  const [selectedAgentId, setSelectedAgentId] = useState<string>("")
  const [keyName, setKeyName] = useState("")
  const [isLoading, setIsLoading] = useState(false)
  const [createdKey, setCreatedKey] = useState<ApiKeyResponse | null>(null)
  const [showKey, setShowKey] = useState(false)

  // 重置表单
  const resetForm = () => {
    setSelectedAgentId("")
    setKeyName("")
    setCreatedKey(null)
    setShowKey(false)
    setIsLoading(false)
  }

  // 处理对话框关闭
  const handleOpenChange = (open: boolean) => {
    if (!open) {
      resetForm()
    }
    onOpenChange(open)
  }

  // 创建API密钥
  const handleCreate = async () => {
    if (!selectedAgentId || !keyName.trim()) {
      toast({
        title: "Please fill in the complete information",
        description: "Please select Agent and enter the key name",
        variant: "destructive",
      })
      return
    }

    try {
      setIsLoading(true)
      const result = await onCreateKey(selectedAgentId, keyName.trim())
      
      if (result) {
        setCreatedKey(result)
        toast({
          title: "API key created",
          description: "Please save your API key; it will only be displayed once.",
        })
      }
    } catch (error) {
      console.error("Failed to create API key:", error)
    } finally {
      setIsLoading(false)
    }
  }

  // 复制API密钥
  const handleCopyKey = () => {
    if (createdKey) {
      navigator.clipboard.writeText(createdKey.apiKey)
      toast({
        title: "Copied to clipboard",
        description: "The API key has been copied to your clipboard.",
      })
    }
  }

  // 完成创建
  const handleFinish = () => {
    resetForm()
    onOpenChange(false)
  }

  return (
    <Dialog open={open} onOpenChange={handleOpenChange}>
      <DialogContent className="sm:max-w-[500px]">
        <DialogHeader>
          <DialogTitle>Create an API key</DialogTitle>
          <DialogDescription>
            Create a new API key to access the RAG Agent Platform API
          </DialogDescription>
        </DialogHeader>

        {!createdKey ? (
          // 创建表单
          <div className="space-y-4 py-4">
            <div className="space-y-2">
              <Label htmlFor="agent-select">Select Agent</Label>
              <Select
                value={selectedAgentId}
                onValueChange={setSelectedAgentId}
                disabled={isLoading}
              >
                <SelectTrigger id="agent-select">
                  <SelectValue placeholder="Please select an Agent" />
                </SelectTrigger>
                <SelectContent>
                  {agents.map((agent) => (
                    <SelectItem key={agent.id} value={agent.id}>
                      {agent.name}
                    </SelectItem>
                  ))}
                </SelectContent>
              </Select>
            </div>

            <div className="space-y-2">
              <Label htmlFor="key-name">Key Name</Label>
              <Input
                id="key-name"
                placeholder="For example: Development environment"
                value={keyName}
                onChange={(e) => setKeyName(e.target.value)}
                disabled={isLoading}
              />
            </div>

            <div className="text-sm text-muted-foreground">
              <p className="font-medium mb-2">Precautions：</p>
              <ul className="list-disc list-inside space-y-1">
                <li>The API key will only be displayed once, please keep it safe.</li>
                <li>The key has full access rights to the corresponding Agent</li>
                <li>Do not use API keys in client code</li>
                <li>Please change your API key regularly to ensure security</li>
              </ul>
            </div>
          </div>
        ) : (
          // 显示创建的密钥
          <div className="space-y-4 py-4">
            <div className="space-y-2">
              <Label htmlFor="new-key">Your new API key</Label>
              <div className="flex items-center gap-2">
                <Input
                  id="new-key"
                  value={showKey ? createdKey.apiKey : "••••••••••••••••••••••••••••••••"}
                  readOnly
                  className="font-mono"
                />
                <Button
                  variant="outline"
                  size="icon"
                  onClick={() => setShowKey(!showKey)}
                >
                  {showKey ? <EyeOff className="h-4 w-4" /> : <Eye className="h-4 w-4" />}
                </Button>
                <Button
                  variant="outline"
                  size="icon"
                  onClick={handleCopyKey}
                >
                  <Copy className="h-4 w-4" />
                </Button>
              </div>
              <p className="text-sm text-muted-foreground flex items-center gap-1">
                <EyeOff className="h-3 w-3" />
                This key will only be displayed once, please copy it immediately and store it safely
              </p>
            </div>

            <div className="bg-muted p-3 rounded-md">
              <p className="text-sm font-medium mb-1">Key information：</p>
              <p className="text-sm">name：{createdKey.name}</p>
              <p className="text-sm">Associate Agent：{createdKey.agentName || createdKey.agentId}</p>
              <p className="text-sm">Creation time：{new Date(createdKey.createdAt).toLocaleString("zh-CN")}</p>
            </div>
          </div>
        )}

        <DialogFooter>
          {!createdKey ? (
            <>
              <Button
                variant="outline"
                onClick={() => onOpenChange(false)}
                disabled={isLoading}
              >
                取消
              </Button>
              <Button
                onClick={handleCreate}
                disabled={isLoading || !selectedAgentId || !keyName.trim()}
              >
                {isLoading ? "Creating..." : "Create a key"}
              </Button>
            </>
          ) : (
            <Button onClick={handleFinish}>
              Finish
            </Button>
          )}
        </DialogFooter>
      </DialogContent>
    </Dialog>
  )
}