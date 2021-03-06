import attachment.Attachment

data class Post(
    val id: Int = 0,          //идентификатор записи.
    val ownerId: Int = 0,          //идентификатор владельца стены, на которой размещена запись.
    val fromId: Int = 0,          //идентификатор автора записи (от чьего имени опубликована запись).
    val createdBy: Int = 0,          //идентификатор администратора, который опубликовал запись (возвращается
    val date: Int = 0,          //время публикации записи в формате unix time.
    val text: String = "",      //текст записи.
    val replyOwnerId: Int = 0,          //идентификатор владельца записи, в ответ на которую была оставлена текущая.
    val replyPostId: Int = 0,          //идентификатор записи, в ответ на которую была оставлена текущая
    val friendsOnly: Boolean = false,  //true, если запись была создана с опцией «Только для друзей».
    val comments: CommentsDesc = CommentsDesc(), //информация о комментариях к записи, объект с полями:
    val copyright: Copyright = Copyright(),    //источник материала
    val likes: Likes = Likes(),        //информация о лайках к записи, объект с полями:
    val reposts: Reposts = Reposts(),      //информация о репостах записи («Рассказать друзьям»),
    val views          : ViewsCount   = ViewsCount(),   //информация о просмотрах записи (число записей)
    val postType: String = "",         //тип записи, может принимать следующие значения: post, copy, reply, postpone, suggest.
    val postSource: PostSource? = null,  //информация о способе размещения записи. Описание объекта
    val attachments: Array<Attachment>? = null, //медиавложения записи (фотографии, ссылки и т.п.)
    val signerId: Int = 0,             //идентификатор автора, если запись была опубликована от имени  сообщества и подписана пользователем;
    val copyHistory: Array<Post>? = null, //массив, содержащий историю репостов для записи.
    val canPin: Boolean = false,     //информация о том, может ли текущий пользователь закрепить запись
    val canDelete: Boolean = false,     //информация о том, может ли текущий пользователь удалить запись
    val canEdit: Boolean = false,     //информация о том, может ли текущий пользователь редактировать запись
    val isPinned: Boolean = false,     //информация о том, что запись закреплена.
    val markedAsAds: Boolean = false,     //информация о том, содержит ли запись отметку "реклама"
    val isFavorite: Boolean = false,     //true, если объект добавлен в закладки у текущего пользователя.
    val donut: VkDonut = VkDonut(), //информация о записи VK Donut:
    val postponedId: Int = 0              //идентификатор отложенной записи. Это поле возвращается тогда, когда запись стояла на таймере.
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Post

        if (id != other.id) return false
        if (ownerId != other.ownerId) return false
        if (fromId != other.fromId) return false
        if (createdBy != other.createdBy) return false
        if (date != other.date) return false
        if (text != other.text) return false
        if (replyOwnerId != other.replyOwnerId) return false
        if (replyPostId != other.replyPostId) return false
        if (friendsOnly != other.friendsOnly) return false
        if (comments != other.comments) return false
        if (copyright != other.copyright) return false
        if (likes != other.likes) return false
        if (reposts != other.reposts) return false
        if (postType != other.postType) return false
        if (postSource != other.postSource) return false
        if (attachments != null) {
            if (other.attachments == null) return false
            if (!attachments.contentEquals(other.attachments)) return false
        } else if (other.attachments != null) return false
        if (signerId != other.signerId) return false
        if (copyHistory != null) {
            if (other.copyHistory == null) return false
            if (!copyHistory.contentEquals(other.copyHistory)) return false
        } else if (other.copyHistory != null) return false
        if (canPin != other.canPin) return false
        if (canDelete != other.canDelete) return false
        if (canEdit != other.canEdit) return false
        if (isPinned != other.isPinned) return false
        if (markedAsAds != other.markedAsAds) return false
        if (isFavorite != other.isFavorite) return false
        if (donut != other.donut) return false
        if (postponedId != other.postponedId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + ownerId
        result = 31 * result + fromId
        result = 31 * result + createdBy
        result = 31 * result + date
        result = 31 * result + text.hashCode()
        result = 31 * result + replyOwnerId
        result = 31 * result + replyPostId
        result = 31 * result + friendsOnly.hashCode()
        result = 31 * result + comments.hashCode()
        result = 31 * result + copyright.hashCode()
        result = 31 * result + likes.hashCode()
        result = 31 * result + reposts.hashCode()
        result = 31 * result + postType.hashCode()
        result = 31 * result + (postSource?.hashCode() ?: 0)
        result = 31 * result + (attachments?.contentHashCode() ?: 0)
        result = 31 * result + signerId
        result = 31 * result + (copyHistory?.contentHashCode() ?: 0)
        result = 31 * result + canPin.hashCode()
        result = 31 * result + canDelete.hashCode()
        result = 31 * result + canEdit.hashCode()
        result = 31 * result + isPinned.hashCode()
        result = 31 * result + markedAsAds.hashCode()
        result = 31 * result + isFavorite.hashCode()
        result = 31 * result + donut.hashCode()
        result = 31 * result + postponedId
        return result
    }
}
data class ViewsCount(
    val count : Int = 0
)
/**
 * Description SourceType
 * тип источника
 */
enum class SourceType(value: String) {
    vk("vk"),// запись создана через основной интерфейс сайта (http://vk.com/);
    widget("widget"),//— запись создана через виджет на стороннем сайте;
    api("api"),//— запись создана приложением через API;
    rss("rss"),//— запись создана посредством импорта RSS-ленты со стороннего сайта;
    sms("sms"),//— запись создана посредством отправки SMS-сообщения на специальный номер.
}

/**
 * Description VkDonut
 * вспомогательный класс, который используется только вместе с Post
 * информация о записи VK Donut
 */
data class VkDonut(
    val isDonut: Boolean = false, // — запись доступна только платным подписчикам VK Donut;
    val paidDuration: Int = 0,         // — время, в течение которого запись будет доступна только платным подписчикам VK Donut;
    val placeholder: String = "",     // — заглушка для пользователей, которые не оформили подписку VK Donut. Отображается вместо содержимого записи
    val canPublishFreeCopy: Boolean = false, // — можно ли открыть запись для всех пользователей, а не только подписчиков VK Donut;
    val editMode: String = "all"   // — информация о том, какие значения VK Donut можно изменить в записи. Возможные значения:
    //all — всю информацию о VK Donut.
    //duration — время, в течение которого запись будет доступна только платным подписчикам VK Donut.
)

/**
 * Description Reposts
 * вспомогательный класс, который используется только вместе с Post
 * информация о репостах записи («Рассказать друзьям»)
 */
data class Reposts(
    val count: Int = 0,
    val userReposted: Boolean = false
) {

}

/**
 * Description Likes
 * вспомогательный класс, который используется только вместе с Post
 * информация о лайках к записи
 */
data class Likes(
    val count: Int = 0,
    val userLikes: Boolean = false,
    val canLike: Boolean = false,
    val canPublish: Boolean = false
) {

}

/**
 * Description CommentsDesc
 * вспомогательный класс, который используется только вместе с Post
 * информация о комментариях к записи
 */

data class CommentsDesc(
    val count: Int = 0,
    val canPost: Boolean = false,
    val groupsCanPost: Boolean = false,
    val canClose: Boolean = false,
    val canOpen: Boolean = false
) {

}

/**
 * Description Copyright
 * вспомогательный класс, который используется только вместе с Post
 * источник материала
 */
data class Copyright(
    val id: Int = 0,
    val link: String = "",
    val name: String = "",
    val type: String = "",
) {

}