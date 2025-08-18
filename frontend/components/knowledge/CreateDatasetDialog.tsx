"use client"

import { useState } from "react"
import { Book, Plus } from "lucide-react"

import { Button } from "@/components/ui/button"
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Textarea } from "@/components/ui/textarea"
import { toast } from "@/hooks/use-toast"

import { createDatasetWithToast } from "@/lib/rag-dataset-service"
import type { CreateDatasetRequest } from "@/types/rag-dataset"

interface CreateDatasetDialogProps {
  onSuccess?: () => void
  trigger?: React.ReactNode
}

export function CreateDatasetDialog({ onSuccess, trigger }: CreateDatasetDialogProps) {
  const [open, setOpen] = useState(false)
  const [isSubmitting, setIsSubmitting] = useState(false)
  const [formData, setFormData] = useState<CreateDatasetRequest>({
    name: "",
    icon: "",
    description: "",
  })

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()

    // 客户端验证
    if (!formData.name.trim()) {
      toast({
        title: "请输入数据集名称",
        variant: "destructive",
      })
      return
    }

    if (formData.name.length > 100) {
      toast({
        title: "数据集名称不能超过100个字符",
        variant: "destructive",
      })
      return
    }

    if (formData.description && formData.description.length > 1000) {
      toast({
        title: "数据集说明不能超过1000个字符",
        variant: "destructive",
      })
      return
    }

    if (formData.icon && formData.icon.length > 500) {
      toast({
        title: "图标URL不能超过500个字符",
        variant: "destructive",
      })
      return
    }

    try {
      setIsSubmitting(true)

      const response = await createDatasetWithToast({
        name: formData.name.trim(),
        icon: formData.icon?.trim() || undefined,
        description: formData.description?.trim() || undefined,
      })

      if (response.code === 200) {
        // 重置表单
        setFormData({
          name: "",
          icon: "",
          description: "",
        })
        setOpen(false)
        onSuccess?.()
      }
    } catch (error) {
      // 错误已由withToast处理
    } finally {
      setIsSubmitting(false)
    }
  }

  const handleInputChange = (field: keyof CreateDatasetRequest, value: string) => {
    setFormData(prev => ({
      ...prev,
      [field]: value
    }))
  }

  return (
    <Dialog open={open} onOpenChange={setOpen}>
      <DialogTrigger asChild>
        {trigger || (
          <Button>
            <Plus className="mr-2 h-4 w-4" />
            Create Dataset
          </Button>
        )}
      </DialogTrigger>
      <DialogContent className="sm:max-w-[425px]">
        <form onSubmit={handleSubmit}>
          <DialogHeader>
            <DialogTitle className="flex items-center gap-2">
              <Book className="h-5 w-5" />
              Create Dataset
            </DialogTitle>
            <DialogDescription>
              Create a new RAG dataset to manage your knowledge documents
            </DialogDescription>
          </DialogHeader>
          
          <div className="grid gap-4 py-4">
            <div className="grid gap-2">
              <Label htmlFor="name">
                Dataset Name <span className="text-red-500">*</span>
              </Label>
              <Input
                id="name"
                placeholder="Please enter a dataset name"
                value={formData.name}
                onChange={(e) => handleInputChange("name", e.target.value)}
                maxLength={100}
                required
              />
              <p className="text-xs text-muted-foreground">
                {formData.name.length}/100 characters
              </p>
            </div>
            
            <div className="grid gap-2">
              <Label htmlFor="icon">Icon URL (optional)</Label>
              <Input
                id="icon"
                placeholder="Please enter the icon URL"
                value={formData.icon}
                onChange={(e) => handleInputChange("icon", e.target.value)}
                maxLength={500}
                type="url"
              />
              <p className="text-xs text-muted-foreground">
                {formData.icon?.length || 0}/500 characters
              </p>
            </div>
            
            <div className="grid gap-2">
              <Label htmlFor="description">Dataset description (optional)</Label>
              <Textarea
                id="description"
                placeholder="Please enter a dataset description"
                value={formData.description}
                onChange={(e) => handleInputChange("description", e.target.value)}
                maxLength={1000}
                rows={3}
              />
              <p className="text-xs text-muted-foreground">
                {formData.description?.length || 0}/1000 characters
              </p>
            </div>
          </div>
          
          <DialogFooter>
            <Button 
              type="button" 
              variant="outline" 
              onClick={() => setOpen(false)}
              disabled={isSubmitting}
            >
              Cancel
            </Button>
            <Button type="submit" disabled={isSubmitting}>
              {isSubmitting ? "Creating..." : "Create"}
            </Button>
          </DialogFooter>
        </form>
      </DialogContent>
    </Dialog>
  )
}