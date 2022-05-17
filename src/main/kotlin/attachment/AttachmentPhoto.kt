package attachment

data class AttachmentPhotoContent(
    val id      : Int = 0,
    val albumId : Int = 0,
    val ownerId : Int = 0,
    val userId  : Int = 0,
    val text    : String = "",
    val date    : Int = 0,
    val sizes   : Array<PhotoSize> = emptyArray<PhotoSize>(),
    val width   : Int = 0,
    val height  : Int = 0,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AttachmentPhotoContent

        if (id != other.id) return false
        if (albumId != other.albumId) return false
        if (ownerId != other.ownerId) return false
        if (userId != other.userId) return false
        if (text != other.text) return false
        if (date != other.date) return false
        if (!sizes.contentEquals(other.sizes)) return false
        if (width != other.width) return false
        if (height != other.height) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + albumId
        result = 31 * result + ownerId
        result = 31 * result + userId
        result = 31 * result + text.hashCode()
        result = 31 * result + date
        result = 31 * result + sizes.contentHashCode()
        result = 31 * result + width
        result = 31 * result + height
        return result
    }
}

data class PhotoSize(
    val type  : String = "", //обозначение размера и пропорций копии.
    val url   : String = "", //url копии изображения;
    val width : Int = 0,     //ширина копии в пикселах
    val height: Int = 0      // высота копии в пикселах;
)