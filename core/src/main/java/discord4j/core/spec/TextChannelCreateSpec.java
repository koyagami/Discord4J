/*
 * This file is part of Discord4J.
 *
 * Discord4J is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Discord4J is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Discord4J.  If not, see <http://www.gnu.org/licenses/>.
 */
package discord4j.core.spec;

import discord4j.common.json.OverwriteEntity;
import discord4j.core.object.PermissionOverwrite;
import discord4j.core.object.entity.Channel;
import discord4j.core.object.util.Snowflake;
import discord4j.rest.json.request.ChannelCreateRequest;

import javax.annotation.Nullable;
import java.util.Set;

public class TextChannelCreateSpec implements Spec<ChannelCreateRequest> {

    private final ChannelCreateRequest.Builder requestBuilder = ChannelCreateRequest.builder()
            .type(Channel.Type.GUILD_TEXT.getValue());

    public TextChannelCreateSpec setName(String name) {
        requestBuilder.name(name);
        return this;
    }

    public TextChannelCreateSpec setTopic(@Nullable String topic) {
        requestBuilder.topic(topic);
        return this;
    }

    public TextChannelCreateSpec setPermissionOverwrites(Set<? extends PermissionOverwrite> permissionOverwrites) {
        OverwriteEntity[] raw = permissionOverwrites.stream()
                .map(o -> new OverwriteEntity(o.getTargetId().asLong(), o.getType().getValue(), o.getAllowed().getRawValue(), o.getDenied().getRawValue()))
                .toArray(OverwriteEntity[]::new);

        requestBuilder.permissionOverwrites(raw);
        return this;
    }

    public TextChannelCreateSpec setParentId(@Nullable Snowflake parentId) {
        requestBuilder.parentId(parentId == null ? null : parentId.asLong());
        return this;
    }

    public TextChannelCreateSpec setNsfw(boolean nsfw) {
        requestBuilder.nsfw(nsfw);
        return this;
    }

    public TextChannelCreateSpec setRateLimitPerUser(int rateLimitPerUser) {
        requestBuilder.rateLimitPerUser(rateLimitPerUser);
        return this;
    }

    @Override
    public ChannelCreateRequest asRequest() {
        return requestBuilder.build();
    }
}
